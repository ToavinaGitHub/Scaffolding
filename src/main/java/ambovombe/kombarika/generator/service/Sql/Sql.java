package ambovombe.kombarika.generator.service.Sql;

import java.io.File;

import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sql {

    
    public String generateSequence(String table) throws Exception{
        String res = "";
        String tempPath = Misc.getSqlTemplateLocation().concat(File.separator).concat("sequence.txt");
        String template = FileUtility.readOneFile(tempPath);
      
        res = template.replace("#table#", table);

        return res;

    }
}