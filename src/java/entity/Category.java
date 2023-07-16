package entity;

public class Category {
    private int categoryId;
    private String title;

    public Category() {
    }

    public Category(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
