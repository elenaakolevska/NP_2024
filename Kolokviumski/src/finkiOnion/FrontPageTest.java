package finkiOnion;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;



/*
class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String category) {
        super(String.format("Category %s was not found",category));
    }
}

abstract class NewsItem{
    private String title;
    private Date date;
    private Category category;

    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return getTeaser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsItem)) return false;
        NewsItem newsItem = (NewsItem) o;
        return Objects.equals(title, newsItem.title) &&
                Objects.equals(date, newsItem.date) &&
                Objects.equals(category, newsItem.category);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, date, category);
    }

    public abstract String getTeaser();
}

class Category implements Comparable<Category> {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Category o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class TextNewsItem extends NewsItem{

    private String text;
    public TextNewsItem(String title, Date date, Category category, String text) {
        super(title, date, category);
        this.text = text;
    }

    public String getText() {
        return text;
    }


    @Override
    public String getTeaser() {
        long minutesAgo = Duration.between(getDate().toInstant(), Instant.now()).toMinutes();
        String shortText = text.length() > 80 ? text.substring(0, 80) : text;
        return String.format("%s\n%d\n%s\n", getTitle(), minutesAgo, shortText);
    }
}
class MediaNewsItem extends NewsItem{
    private String url;
    private int views;
    public MediaNewsItem(String title, Date date, Category category, String url, int views) {
        super(title, date, category);
        this.url=url;
        this.views=views;
    }

    public String getUrl() {
        return url;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String getTeaser() {
        long minutesAgo = Duration.between(getDate().toInstant(), Instant.now()).toMinutes();
        return String.format("%s\n%d\n%s\n%d\n", getTitle(), minutesAgo, url, views);
    }
}

class FrontPage{

    private Category[] categories;
    private List<NewsItem> newsItems;



    public FrontPage(Category[] categories) {
        newsItems = new ArrayList<>();
        this.categories = new Category[categories.length];
        System.arraycopy(categories, 0, this.categories, 0, categories.length);
    }

    public void addNewsItem(NewsItem newsItem) {
        newsItems.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category) {
        return newsItems.stream().filter(i->i.getCategory().equals(category)).collect(Collectors.toList());
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        boolean categoryExists = Arrays.stream(categories).anyMatch(c -> c.getName().equals(category));
        if (!categoryExists) {
            throw new CategoryNotFoundException(category);
        }

        return newsItems.stream()
                .filter(i -> i.getCategory().getName().equals(category))
                .collect(Collectors.toList());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        newsItems.forEach(sb::append);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrontPage)) return false;
        FrontPage frontPage = (FrontPage) o;
        return Arrays.equals(categories, frontPage.categories) &&
                Objects.equals(newsItems, frontPage.newsItems);
    }


    @Override
    public int hashCode() {
        int result = Objects.hash(newsItems);
        result = 31 * result + Arrays.hashCode(categories);
        return result;
    }
}
public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


*/

class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}


class Category implements Comparable<Category>{
    private String nameCategory;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public int compareTo(Category o) {
        return this.nameCategory.compareTo(o.nameCategory);
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(nameCategory, category.nameCategory);
    }


    @Override
    public int hashCode() {
        return Objects.hash(nameCategory);
    }
}


abstract class NewsItem{
    private String title;
    private Date datePublished;
    private Category category;

    public NewsItem(String title, Date datePublished, Category category) {
        this.title = title;
        this.datePublished = datePublished;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return getTeaser();
    }


    public abstract String getTeaser();
}

class TextNewsItem extends NewsItem{

    private String text;
    public TextNewsItem(String title, Date datePublished, Category category, String text) {
        super(title, datePublished, category);
        this.text = text;
    }


    @Override
    public String getTeaser() {
        long minutesAgo = Duration.between(getDatePublished().toInstant(), Instant.now()).toMinutes();
        String shortText = text.length() > 80 ? text.substring(0, 80) : text;
        return String.format("%s\n%d\n%s\n", getTitle(), minutesAgo, shortText);
    }
}

class MediaNewsItem extends NewsItem{
    private String url;
    private int numViews;

    public MediaNewsItem(String title, Date datePublished, Category category,String url, int numViews) {
        super(title, datePublished, category);
        this.url = url;
        this.numViews = numViews;
    }

    @Override
    public String getTeaser() {
        long minutesAgo = Duration.between(getDatePublished().toInstant(), Instant.now()).toMinutes();
        return String.format("%s\n%d\n%s\n%d\n", getTitle(), minutesAgo, url, numViews);
    }
}

class FrontPage{
    private List<NewsItem> newsItems;
    private Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.newsItems = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem){
        newsItems.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category){
       return newsItems.stream().filter(i->i.getCategory().equals(category)).collect(Collectors.toList());
    }


    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {

        boolean categoryExists = Arrays.stream(categories).anyMatch(i->i.getNameCategory().equals(category));
        if (!categoryExists){
            throw new CategoryNotFoundException(String.format("Category %s was not found", category));
        }
       return newsItems.stream().filter(i -> i.getCategory().getNameCategory().equals(category)).collect(Collectors.toList());

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        newsItems.forEach(sb::append);
        return sb.toString();
    }
}




public class FrontPageTest {
    public static void main(String[] args) {
        // Reading
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
