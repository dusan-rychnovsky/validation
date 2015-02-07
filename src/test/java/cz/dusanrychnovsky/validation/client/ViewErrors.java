package cz.dusanrychnovsky.validation.client;

import cz.dusanrychnovsky.validation.*;
import cz.dusanrychnovsky.validation.Error;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ViewErrors {

    private final Crawler crawler = new Crawler();

    private final Object obj;
    private final Errors errors;

    /**
     *
     * @param obj
     * @param errors
     */
    public ViewErrors(Object obj, Errors errors) {
        this.obj = obj;
        this.errors = errors;
    }

    /**
     * Prints the list of errors into the given stream in a human-readable form.
     *
     * @param out
     * @throws IOException
     */
    public void print(OutputStream out) throws IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        writer.write("ERRORS:");
        writer.newLine();

        for (Error error : errors) {
            print(error, writer);
            writer.newLine();
        }

        writer.write("DONE");
        writer.newLine();

        writer.flush();
    }

    /**
     *
     * @param error
     * @param writer
     */
    public void print(Error error, BufferedWriter writer) throws IOException {

        writer.write("* ");
        writer.write(error.getMessage().toString());
        writer.write(" ");
        writer.write("[");
        writer.write(StringUtils.join(getValues(error), ", "));
        writer.write("]");
    }

    /**
     *
     * @param error
     * @return
     */
    private List<String> getValues(Error error) {

        List<String> result = new ArrayList<>();
        for (Path path : error.getFieldPaths()) {
            result.add(crawler.getValue(obj, path));
        }

        return result;
    }
}
