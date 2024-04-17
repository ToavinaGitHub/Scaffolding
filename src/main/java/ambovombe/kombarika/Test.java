package ambovombe.kombarika;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 *  @author Mamisoa
 */
public class Test {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator();
        String path = "./";
        String framework = "java:spring-boot";
        String packageName = "com.cinepax.mg";
        String entity = "Model";
        String controller = "Controller";
        String repository = "Repository";
        String view = "views/view";
        String viewType = "react-ionic";
        String url = "http://localhost:8080/";
        try{
            String[] tables = DbService.getAllTablesArrays(codeGenerator.getDbConnection());

            codeGenerator.generateAll(path, packageName, entity, controller, repository, view, viewType, url, tables, framework);

            //codeGenerator.generateAllControllerThymeleaf("./", tables, "com.cinepax.mg", "java:spring");
            
            //codeGenerator.generateAllThymeleafView(path, tables, view, "thymeleaf", "Tsakitsaky");

            //codeGenerator.generateFileSequence("./", tables);

        //   codeGenerator.generateLoginView(url, "login","utilisateur", "email", "password", viewType, view, path);
       
        //    DbConnection db = new DbConnection();
        //    db.init(); 

        //    List<String> cols = new ArrayList<>();
        //    cols.add("email");
        //    cols.add("password");

        //    System.out.println(DbService.checkIfTableExist(db, "utilisateur"));


            //HashMap<String,String> cols = DbService.getColumnNameAndType(db.getConnection(), "utilisateur");
            
            // codeGenerator.generateEntity(path, "car", packageName+".entity", framework);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }
    }
}
