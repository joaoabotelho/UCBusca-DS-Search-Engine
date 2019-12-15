package com.botelho.rmi.server;

import com.botelho.commons.User;
import com.botelho.commons.UserType;
import com.botelho.commons.WebPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Storage implements Serializable, Closeable {
    private static final int MAX_DEPTH = 4;
    private static Storage instance;
    private static Logger logger = LoggerFactory.getLogger(Storage.class);
    private final HashMap<String, User> users = new HashMap<>();
    private HashMap<String, HashSet<WebPage>> index = new HashMap<>();
    private HashSet<String> links = new HashSet<>(); // HashSet of urls
    private int next_web_page_id = 0;

    private Storage() {
        logger.info("Seeding database");
        /* Get best word to search with the most results
        String best = "example";
        Integer best_size = 0;
        for (String key: index.keySet()) {
            Integer value_count = index.get(key).size();
            if(best_size < value_count){
                best = key;
                best_size = value_count;
            }
        }
        logger.info("BEST VALUE {} with {} results.", best, best_size);
         */
        logger.info("Database seeded");
    }

    public static Storage getInstance() {
        if (instance == null) {
            synchronized(Storage.class) {
                if (instance == null) {
                    instance = new Storage();
                }
            }
        }
        return instance;
    }

    public static void setInstance(Storage storage) {
        if (instance == null) {
            synchronized(Storage.class) {
                if (instance == null) {
                    instance = storage;
                    return;
                }
            }
        }
        throw new RuntimeException("This class was already instanced.");
    }

    public Boolean newUrlToIndex(String url, int depth) throws IOException {
        // If link doesnt exist, depth of recursion it not passed and valid Url
        if (!links.contains(url) && (depth < MAX_DEPTH) && isUrlValid(url)) {
            Document doc = Jsoup.connect(url).get();  // Documentation: https://jsoup.org/

            WebPage page = newWebPage(url, doc.title(), "Hello World", countWords(doc.text()));
            addToIndexStringToWebPage(url, page);
            addWebPageToIndex(page);
            depth++;
            Elements links = doc.select("a[href]");
            ArrayList<String> final_links = new ArrayList<>();
            for (Element link : links) {
                // Ignore bookmarks within the page
                if (link.attr("href").startsWith("#")) {
                    continue;
                }

                // Shall we ignore local links? Otherwise we have to rebuild them for future parsing
                if (!link.attr("href").startsWith("http")) {
                    continue;
                }

                String link_url = link.attr("abs:href");
                final_links.add(link_url);
                addToIndexStringToWebPage(link_url, page);
                newUrlToIndex(link_url, depth);
            }
            page.setLinks(final_links);
        }
        return true;
    }

    User userFind(String username){
        return users.get(username);
    }

    public Boolean promoteUser(String username) {
        User user = userFind(username);
        if(user != null) {
            logger.info("User found to promote: {}", user.getUsername());
            user.setType(UserType.ADMIN);
            return true;
        } else {
            logger.info("User not found.");
            return false;
        }
    }

    public void seedStorage() {
        try {
            URI path;
            try {
                path = getClass().getResource("/seed_urls.txt").toURI();
                Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                FileSystems.newFileSystem(path, env);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            List<String> allLines = Files.readAllLines(Paths.get(path));
            for (String url : allLines) {
                // Attempt to connect and get the document
                newUrlToIndex(url, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addWebPageToIndex(WebPage page) {
        for (String word : page.words.keySet()) {
            addToIndexStringToWebPage(word, page);
        }
    }

    void addToIndexStringToWebPage(String key, WebPage page) {
        // add a page to the inverted index
        logger.info("Adding key: {} to index", key);
        HashSet<WebPage> associated_pages = index.get(key);
        if (associated_pages == null) {
            associated_pages = new HashSet<>();
            index.put(key, associated_pages);
        }
        associated_pages.add(page);
    }

    private Boolean isUrlValid(String url) {
        try {
            Jsoup.connect(url).get();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean authenticateUser(User user) {
        if (users.containsKey(user.getUsername())) {
            return users.get(user.getUsername()).getPassword().equals(user.getPassword());
        }
        return false;
    }

    public User getUser(User user) {
        if(authenticateUser(user)) {
            return users.get(user.getUsername());
        }
        return null;
    }

    WebPage newWebPage(String url, String title, String citation, Map<String, Integer> words) {
        WebPage page = new WebPage(next_web_page_id, url, title, citation, words);
        next_web_page_id++;
        links.add(url);
        return page;
    }


    public ArrayList<WebPage> searchFor(List<String> data, User user) {
        /* OLD CODE: existing data?
        Search exiting_search = search_requests.get(data);
        if (exiting_search == null) {
        */
        HashSet<WebPage> pages = new HashSet<>();

        for (String word : data) {
            HashSet<WebPage> associated_pages = index.get(word);
            if (associated_pages != null) {
                pages.addAll(associated_pages);
            }
        }

        ArrayList<WebPage> list = new ArrayList<>(pages);
        Collections.sort(list, (lhs, rhs) -> Integer.compare(rhs.link_count, lhs.link_count));

        /* OLD CODE Record of search to User
        Search new_search = new Search(next_search_id, data, list);
        search_requests.put(data, new_search);
        search_freq.put(data, 1);
        if (user != null) {
            User listed_user = this.user_find(user.id);
            if(listed_user.previous_searches == null){
                listed_user.previous_searches = new ArrayList<>();
            }
            listed_user.previous_searches.add(new_search);
        }
         */
        logger.info("Got this pages: " + Arrays.toString(pages.toArray()));
        logger.info("Got this list: " + Arrays.toString(list.toArray()));
        return list;
            /* OLD CODE id existing data
        } else {
            Integer n_times_searched = search_freq.get(data);
            search_freq.put(data, n_times_searched+1);
            return exiting_search;
            }
             */
    }

    private Map<String, Integer> countWords(String text) {
        Map<String, Integer> countMap = new TreeMap<>();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
        String line;

        // Get words and respective count
        while (true) {
            try {
                if ((line = reader.readLine()) == null)
                    break;
                String[] words = line.split("[ ,;:.?!“”(){}\\[\\]<>']+");
                for (String word : words) {
                    word = word.toLowerCase();
                    if ("".equals(word)) {
                        continue;
                    }
                    if (!countMap.containsKey(word)) {
                        countMap.put(word, 1);
                    } else {
                        countMap.put(word, countMap.get(word) + 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Close reader
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countMap;
    }

    public boolean createUser(User user) {
        if(!users.containsKey(user.getUsername())) {
            if(users.isEmpty()){
               user.setType(UserType.ADMIN);
            }
            users.put(user.getUsername(), user);
            return true;
        }
        return false;

    }

    public void close() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./storage");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(Storage.getInstance());
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
