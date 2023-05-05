package cl.rivendel.csv.helper;

import cl.rivendel.csv.mapper.CSVMapper;
import cl.rivendel.csv.model.jumpseller.CSVModel;
import cl.rivendel.csv.model.scryfall.Card;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CSVHelper {
    private static final Logger log = LoggerFactory.getLogger(CSVHelper.class);

    private final CSVMapper csvMapper;

    public CSVHelper(@Autowired CSVMapper csvMapper) {
        super();
        this.csvMapper = csvMapper;
    }


    public void generateJumpSellerCSV(List<CSVModel> csvCards, String outputFileName) {
        try(Writer writer = new FileWriter(outputFileName)) {
            writer.append(buildHeader(CSVModel.class));
            StatefulBeanToCsv<CSVModel> statefulBeanToCsv = new StatefulBeanToCsvBuilder<CSVModel>(writer)
                    .withSeparator('\t')
                    .build();
            statefulBeanToCsv.write(csvCards);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            log.error(e.getMessage());
        }
    }

    private String buildHeader(Class<CSVModel> clazz) {
        return Arrays.stream( clazz.getDeclaredFields() )
                .filter( f -> f.getAnnotation( CsvBindByPosition.class ) != null
                        && f.getAnnotation( CsvBindByName.class ) != null )
                .sorted( Comparator.comparing(f -> f.getAnnotation( CsvBindByPosition.class ).position() ) )
                .map( f -> f.getAnnotation( CsvBindByName.class ).column() )
                .collect( Collectors.joining( "\t" ) ) + "\n";
    }

    public List<CSVModel> cardListToCsvModelList(List<Card> cardList) {
        List<CSVModel> returnedList = new ArrayList<>();
        for (Card card: cardList) {
            try {
                returnedList.add(csvMapper.toCsvModel(card));
            } catch (Exception e){
                log.error("Error during csv model creation", e);
                log.error("Card name={}", card.getName());
            }
        }
        return returnedList;
    }

    public void updateImagesUris(List<Card> cardsList, Map<String, String> newValues) {
        for (Card card: cardsList) {
            log.info("Setting card image url: {}", newValues.get(card.getCollectorNumber()));
            card.getImageUris().put("imgBB", newValues.get(card.getCollectorNumber()));
        }
    }
}
