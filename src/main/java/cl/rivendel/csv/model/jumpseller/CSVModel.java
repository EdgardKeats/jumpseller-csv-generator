package cl.rivendel.csv.model.jumpseller;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class CSVModel {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "Permalink")
    private String permalink;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "Description")
    private String description;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "Meta Title")
    private String metaTitle;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "Meta Description")
    private String metaDescription;

    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "Width")
    private String width;

    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "Length")
    private String length;

    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "Height")
    private String height;

    @CsvBindByPosition(position = 8)
    @CsvBindByName(column = "Brand")
    private String brand;

    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "Barcode")
    private String barcode;

    @CsvBindByPosition(position = 10)
    @CsvBindByName(column = "Categories")
    private String categories;

    @CsvBindByPosition(position = 11)
    @CsvBindByName(column = "Images")
    private String images;

    @CsvBindByPosition(position = 12)
    @CsvBindByName(column = "Digital")
    private String digital;

    @CsvBindByPosition(position = 13)
    @CsvBindByName(column = "Featured")
    private String featured;

    @CsvBindByPosition(position = 14)
    @CsvBindByName(column = "Status")
    private String status;

    @CsvBindByPosition(position = 15)
    @CsvBindByName(column = "SKU")
    private String sku;

    @CsvBindByPosition(position = 16)
    @CsvBindByName(column = "Weight")
    private String weight;

    @CsvBindByPosition(position = 17)
    @CsvBindByName(column = "Stock")
    private String stock;

    @CsvBindByPosition(position = 18)
    @CsvBindByName(column = "Stock Unlimited")
    private String stockUnlimited;

    @CsvBindByPosition(position = 19)
    @CsvBindByName(column = "Price")
    private String price;


    @CsvBindByPosition(position = 20)
    @CsvBindByName(column = "Variant 1 Option Name")
    private String variantOneOptionName;

    @CsvBindByPosition(position = 21)
    @CsvBindByName(column = "Variant 1 Option Type")
    private String variantOneOptionType;

    @CsvBindByPosition(position = 22)
    @CsvBindByName(column = "Variant 1 Option Value")
    private String variantOneOptionValue;



    @CsvBindByPosition(position = 23)
    @CsvBindByName(column = "Custom Field 1 Label")
    private String customFieldOneLabel;

    @CsvBindByPosition(position = 24)
    @CsvBindByName(column = "Custom Field 1 Value")
    private String customFieldOneValue;

    @CsvBindByPosition(position = 25)
    @CsvBindByName(column = "Custom Field 1 Type")
    private String customFieldOneType;

    @CsvBindByPosition(position = 26)
    @CsvBindByName(column = "Custom Field 2 Label")
    private String customFieldTwoLabel;

    @CsvBindByPosition(position = 27)
    @CsvBindByName(column = "Custom Field 2 Value")
    private String customFieldTwoValue;

    @CsvBindByPosition(position = 28)
    @CsvBindByName(column = "Custom Field 2 Type")
    private String customFieldTwoType;

    @CsvBindByPosition(position = 29)
    @CsvBindByName(column = "Custom Field 3 Label")
    private String customFieldThreeLabel;

    @CsvBindByPosition(position = 30)
    @CsvBindByName(column = "Custom Field 3 Value")
    private String customFieldThreeValue;

    @CsvBindByPosition(position = 31)
    @CsvBindByName(column = "Custom Field 3 Type")
    private String customFieldThreeType;

    @CsvBindByPosition(position = 32)
    @CsvBindByName(column = "Custom Field 4 Label")
    private String customFieldFourLabel;

    @CsvBindByPosition(position = 33)
    @CsvBindByName(column = "Custom Field 4 Value")
    private String customFieldFourValue;

    @CsvBindByPosition(position = 34)
    @CsvBindByName(column = "Custom Field 4 Type")
    private String customFieldFourType;

    @CsvBindByPosition(position = 35)
    @CsvBindByName(column = "Google Product Category")
    private String googleProductCategory;

    public CSVModel() {
    }

    public CSVModel(String permalink, String name, String description, String metaTitle, String metaDescription, String width, String length, String height, String brand, String barcode, String categories, String images, String digital, String featured, String status, String sku, String weight, String stock, String stockUnlimited, String price, String variantOneOptionName, String variantOneOptionType, String variantOneOptionValue, String customFieldOneLabel, String customFieldOneValue, String customFieldOneType, String customFieldTwoLabel, String customFieldTwoValue, String customFieldTwoType, String customFieldThreeLabel, String customFieldThreeValue, String customFieldThreeType, String customFieldFourLabel, String customFieldFourValue, String customFieldFourType, String googleProductCategory) {
        this.permalink = permalink;
        this.name = name;
        this.description = description;
        this.metaTitle = metaTitle;
        this.metaDescription = metaDescription;
        this.width = width;
        this.length = length;
        this.height = height;
        this.brand = brand;
        this.barcode = barcode;
        this.categories = categories;
        this.images = images;
        this.digital = digital;
        this.featured = featured;
        this.status = status;
        this.sku = sku;
        this.weight = weight;
        this.stock = stock;
        this.stockUnlimited = stockUnlimited;
        this.price = price;
        this.variantOneOptionName = variantOneOptionName;
        this.variantOneOptionType = variantOneOptionType;
        this.variantOneOptionValue = variantOneOptionValue;

        this.customFieldOneLabel = customFieldOneLabel;
        this.customFieldOneValue = customFieldOneValue;
        this.customFieldOneType = customFieldOneType;
        this.customFieldTwoLabel = customFieldTwoLabel;
        this.customFieldTwoValue = customFieldTwoValue;
        this.customFieldTwoType = customFieldTwoType;
        this.customFieldThreeLabel = customFieldThreeLabel;
        this.customFieldThreeValue = customFieldThreeValue;
        this.customFieldThreeType = customFieldThreeType;
        this.customFieldFourLabel = customFieldFourLabel;
        this.customFieldFourValue = customFieldFourValue;
        this.customFieldFourType = customFieldFourType;
        this.googleProductCategory = googleProductCategory;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockUnlimited() {
        return stockUnlimited;
    }

    public void setStockUnlimited(String stockUnlimited) {
        this.stockUnlimited = stockUnlimited;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVariantOneOptionName() {
        return variantOneOptionName;
    }

    public void setVariantOneOptionName(String variantOneOptionName) {
        this.variantOneOptionName = variantOneOptionName;
    }

    public String getVariantOneOptionType() {
        return variantOneOptionType;
    }

    public void setVariantOneOptionType(String variantOneOptionType) {
        this.variantOneOptionType = variantOneOptionType;
    }

    public String getVariantOneOptionValue() {
        return variantOneOptionValue;
    }

    public void setVariantOneOptionValue(String variantOneOptionValue) {
        this.variantOneOptionValue = variantOneOptionValue;
    }

    public String getCustomFieldOneLabel() {
        return customFieldOneLabel;
    }

    public void setCustomFieldOneLabel(String customFieldOneLabel) {
        this.customFieldOneLabel = customFieldOneLabel;
    }

    public String getCustomFieldOneValue() {
        return customFieldOneValue;
    }

    public void setCustomFieldOneValue(String customFieldOneValue) {
        this.customFieldOneValue = customFieldOneValue;
    }

    public String getCustomFieldOneType() {
        return customFieldOneType;
    }

    public void setCustomFieldOneType(String customFieldOneType) {
        this.customFieldOneType = customFieldOneType;
    }

    public String getCustomFieldTwoLabel() {
        return customFieldTwoLabel;
    }

    public void setCustomFieldTwoLabel(String customFieldTwoLabel) {
        this.customFieldTwoLabel = customFieldTwoLabel;
    }

    public String getCustomFieldTwoValue() {
        return customFieldTwoValue;
    }

    public void setCustomFieldTwoValue(String customFieldTwoValue) {
        this.customFieldTwoValue = customFieldTwoValue;
    }

    public String getCustomFieldTwoType() {
        return customFieldTwoType;
    }

    public void setCustomFieldTwoType(String customFieldTwoType) {
        this.customFieldTwoType = customFieldTwoType;
    }

    public String getCustomFieldThreeLabel() {
        return customFieldThreeLabel;
    }

    public void setCustomFieldThreeLabel(String customFieldThreeLabel) {
        this.customFieldThreeLabel = customFieldThreeLabel;
    }

    public String getCustomFieldThreeValue() {
        return customFieldThreeValue;
    }

    public void setCustomFieldThreeValue(String customFieldThreeValue) {
        this.customFieldThreeValue = customFieldThreeValue;
    }

    public String getCustomFieldThreeType() {
        return customFieldThreeType;
    }

    public void setCustomFieldThreeType(String customFieldThreeType) {
        this.customFieldThreeType = customFieldThreeType;
    }

    public String getCustomFieldFourLabel() {
        return customFieldFourLabel;
    }

    public void setCustomFieldFourLabel(String customFieldFourLabel) {
        this.customFieldFourLabel = customFieldFourLabel;
    }

    public String getCustomFieldFourValue() {
        return customFieldFourValue;
    }

    public void setCustomFieldFourValue(String customFieldFourValue) {
        this.customFieldFourValue = customFieldFourValue;
    }

    public String getCustomFieldFourType() {
        return customFieldFourType;
    }

    public void setCustomFieldFourType(String customFieldFourType) {
        this.customFieldFourType = customFieldFourType;
    }

    public String getGoogleProductCategory() {
        return googleProductCategory;
    }

    public void setGoogleProductCategory(String googleProductCategory) {
        this.googleProductCategory = googleProductCategory;
    }
}
