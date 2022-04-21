package cl.rivendel.csv.model.jumpseller;

public class VariantColor {
    private String variantImage;
    private String optionName;
    private String optionType;
    private String optionValue;

    public String getVariantImage() {
        return variantImage;
    }

    public void setVariantImage(String variantImage) {
        this.variantImage = variantImage;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    @Override
    public String toString() {
        return "VariantColor{" +
                "variantImage='" + variantImage + '\'' +
                ", variantOneOptionName='" + optionName + '\'' +
                ", variantOneOptionType='" + optionType + '\'' +
                ", variantOneOptionValue='" + optionValue + '\'' +
                '}';
    }
}
