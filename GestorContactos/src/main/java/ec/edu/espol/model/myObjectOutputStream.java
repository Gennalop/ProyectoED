
package ec.edu.espol.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class myObjectOutputStream extends ObjectOutputStream implements Serializable{
    
    public myObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public myObjectOutputStream() throws IOException{
        super();
    }
    
    @Override
    public void writeStreamHeader() throws IOException
    {
        reset();
    }
}
