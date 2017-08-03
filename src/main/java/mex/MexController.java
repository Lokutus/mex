package mex;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

@RestController
public class MexController {

    private static final String VOWELS = "aeiouyáéíóúůýäëïöüÿ";
    private static final String REPLACE_PATTERN = "\\s{2,}";
    private static final String REPLACE_TO = " ";

    @RequestMapping("/mex")
    public Mex mex(@RequestParam(value = "text", defaultValue = "xem") String text) {

        String result = text.replaceAll(REPLACE_PATTERN, REPLACE_TO);

        String reversed = IntStream.range(1, result.length() + 1)
                .mapToObj(i -> result.length() - i)
                .map(text::charAt)
                .map(Object::toString)
                .map(s -> VOWELS.contains(s) ? s.toUpperCase() : s.toLowerCase())
                .collect(joining(""));

        return new Mex(reversed);
    }
}
