/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.generator;

import ambovombe.kombarika.configuration.main.LanguageDetails;
import ambovombe.kombarika.configuration.main.TypeProperties;
import ambovombe.kombarika.configuration.main.ViewDetails;
import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.service.controller.Controller;
import ambovombe.kombarika.generator.service.Sql.*;
import ambovombe.kombarika.generator.service.entity.Entity;
import ambovombe.kombarika.generator.service.repository.Repository;
import ambovombe.kombarika.generator.service.view.View;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;

import javax.xml.crypto.Data;


/**
 * @author Mamisoa
 * @author rakharrs
 */
@Getter @Setter
public class CodeGenerator {
    DbConnection dbConnection;
    LanguageDetails languageDetails;
    TypeProperties typeProperties;
    ViewDetails viewDetails;
    FrameworkProperties frameworkProperties;

    public CodeGenerator() throws Exception {
        this.dbConnection = new DbConnection();
        try {
            this.dbConnection.init();
        } catch (Exception e) {
           
        }
        
        this.languageDetails = new LanguageDetails();
        this.languageDetails.init();
        this.typeProperties = new TypeProperties();
        this.typeProperties.init();
        this.viewDetails = new ViewDetails();
        this.viewDetails.init();
    }

    public void generateEntity(
        String path, 
        String table, 
        String packageName, 
        String lang)
    throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        this.setFrameworkProperties(this.getLanguageDetails().getLanguages().get(language).getFrameworks().get(framework));
        generateEntityFile(path, table, packageName, language, framework);
    }

    public void generateController(
        String path, 
        String table, 
        String packageName, 
        String repository, 
        String entity, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String controller = buildController(table, packageName, repository, entity, language, framework);
        generateControllerFile(path, table, packageName, language, framework, controller);
    }

    public void generateControllerThymeleaf(
        String path, 
        String table, 
        String packageName, 
        String lang,
        String idType
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String controller = buildControllerThymeleaf(table, packageName, language, framework,idType);
        generateControllerThymeleafFile(path, table, packageName, language, framework, controller);
    }

    public void generateRepositoryFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        if(languageProperties.getFrameworks().get(framework).getRepository().equals("")) return;
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory, path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+languageProperties.getFrameworks().get(framework).getRepositoryProperty().getName(), languageProperties.getExtension()), content);
    }

    public String buildRepository(
        String table, 
        String packageName, 
        String entityPackage, 
        String language, 
        String framework
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        List<String> primaryKeysType = DbService.getPrimaryKeyType(dbConnection, table);
        Repository repository = new Repository();
        repository.setFrameworkProperties(frameworkProperties);
        repository.setLanguageProperties(languageProperties);
        repository.setTypeMapping(typeMapping);
        return repository.generateRepository(table, packageName, entityPackage, primaryKeysType);
    }

    public String buildRepository(
        String[] tables, 
        String context,
        String packageName, 
        String entityPackage, 
        String language, 
        String framework
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        List<String> primaryKeysType = DbService.getPrimaryKeyType(dbConnection, tables[0]);
        Repository repository = new Repository();
        repository.setFrameworkProperties(frameworkProperties);
        repository.setLanguageProperties(languageProperties);
        repository.setTypeMapping(typeMapping);
        return repository.generateRepository(tables, context, packageName, entityPackage, primaryKeysType);
    }
    

    public void generateRepository(
        String path, 
        String table, 
        String packageName, 
        String entityPackage, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String repository = buildRepository(table, packageName, entityPackage, language, framework);
        
        generateRepositoryFile(path, table, packageName, language, framework, repository);
    }

    public void generateRepository(
        String path, 
        String[] tables, 
        String context,
        String packageName, 
        String entityPackage, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String repository = buildRepository(tables, context, packageName, entityPackage, language, framework);
        generateRepositoryFile(path, context, packageName, language, framework, repository);
    }

    public void generateView(
        String path, 
        String table,
        String directory, 
        String viewType,
        String url
    ) throws Exception{
        String view = buildView(table, viewType, url);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName(table, this.getViewDetails().getViews().get(viewType).getExtension());
        FileUtility.generateFile(path, fileName, view);
    }
    public void generateThymeleafView(
        String path, 
        String viewType,
        String table,
        String project,
        String directory
    ) throws Exception{
        String view = buildViewThymeleaf(table, project, viewType);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName(table, this.getViewDetails().getViews().get(viewType).getExtension());
        FileUtility.generateFile(path, fileName, view);
    }


    public void generateLoginView(
        String url, 
        String endPoint,
        String tableName,
        String email, 
        String password,
        String viewType,
        String directory,
        String path
    ) throws Exception{
        String view = buildLoginView(url, endPoint,tableName, email, password, viewType);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName("Login", this.getViewDetails().getViews().get(viewType).getExtension());
        FileUtility.generateFile(path, fileName, view);
    }

    /**
     * eg : generate -p path -t table1, table2, table3 -package name -l java:spring-boot
     * @author rakharrs
     */
    public String buildEntity(String table, String packageName, String language, String framework) throws Exception {
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Entity entity = new Entity();
        entity.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        entity.setLanguageProperties(languageProperties);
        entity.setTypeMapping(typeMapping);
        entity.setImports(frameworkProperties.getImports());
        return entity.generateEntity(getDbConnection(), template, table, packageName);
    }

    public void generateEntityFile(
        String path, 
        String table, 
        String packageName, 
        String language, 
        String framework
    ) throws Exception{
        String entity = buildEntity(table, packageName, language, framework);
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table, languageProperties.getExtension()), entity);
    }

    public String buildController(String table, String packageName, String repository, String entity, String language, String framework) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Controller controller = new Controller();
        controller.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        controller.setControllerProperty(frameworkProperties.getControllerProperty());
        controller.setCrudMethod(frameworkProperties.getCrudMethod());
        controller.setImports(frameworkProperties.getImports());
        controller.setLanguageProperties(languageProperties);
        return controller.generateController(template, table, packageName, repository, entity, framework);
    }


    public String buildControllerThymeleaf(String table,String packageName, String language,String framework,String idType) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Controller controller = new Controller();
        return controller.generateThymeleafController(template,packageName,table,idType);
    }

    public void generateControllerThymeleafFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+"Controller", languageProperties.getExtension()), content);
    }

    public void generateControllerFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+"Controller", languageProperties.getExtension()), content);
    }

    public String buildView(String table, String viewType, String url) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        return view.generateView(table, url, dbConnection);
    }

    public String buildLoginView(String url , String endPoint , String tableName,String email , String password ,String viewType ) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        return view.generateLoginView(url, endPoint,tableName, email, password);
    }
    
    public String buildViewThymeleaf(String table,String project, String viewType) throws Exception{
        View view = new View();
        view.setViewProperties(this.getViewDetails().getViews().get(viewType));
        return view.generateThymeleafView(table, project,dbConnection);
    }
    
    public String buildSequenceSql(String table) throws Exception{
        Sql sql = new Sql();
        return sql.generateSequence(table);
    }


    public void generateFileSequence(String path, String[] tables) throws Exception{

        String res = "";
        for (String table : tables) {
            res += buildSequenceSql(table);
        }
        FileUtility.generateFile(path, "sequence.txt", res);
    
    }
    public void generateAllEntity(
        String path, 
        String[] tables, 
        String packageName, 
        String entity, 
        String framework
    )  throws Exception{
        for (String table : tables) {
            generateEntity(path, table, packageName + "." + entity, framework);
        }
    }
    public void generateAllController(
        String path, 
        String[] tables,
        String packageName, 
        String entity, 
        String controller, 
        String repository,
        String framework
    )  throws Exception{
        for (String table : tables) {
            generateController(path, table, packageName + "." + controller, packageName + "." + repository, packageName + "." + "entity", framework);  
        }
    }
    public void generateAllControllerThymeleaf(
        String path, 
        String[] tables,
        String packageName, 
        String framework
    )  throws Exception{
        for (String table : tables) {
           generateControllerThymeleaf(path, ObjectUtility.formatToCamelCase(table), packageName, framework,DbService.getPrimaryKeyType(dbConnection, table).get(0));
        }
    }
    
    public void generateAllRepository(
        String path, 
        String[] tables,
        String packageName, 
        String entity,
        String repository,
        String framework
    )  throws Exception{
        if(this.getFrameworkProperties().isOneRepository()){
            generateRepository(path, tables, ObjectUtility.capitalize(repository), packageName + "." + repository, entity, framework);
        }else{
            for (String table : tables) {
                generateRepository(path, table, packageName + "." + repository, packageName + "." + entity, framework);
            }
        }
    }

    public void generateAllView(
        String path, 
        String[] tables,
        String view,
        String viewType,
        String url
    )  throws Exception{
        for (String table : tables) {
            generateView(path, table, view, viewType, url); 
        }
        
    }

    public void generateAllThymeleafView(
        String path, 
        String[] tables,
        String view,
        String viewType,
        String project
    )  throws Exception{
        for (String table : tables) {
            generateThymeleafView(path, viewType, ObjectUtility.formatToCamelCase(table), project, view);
        }
        
    }

    public void generateAll(
        String path, 
        String packageName, 
        String entity, 
        String controller, 
        String repository,
        String view,
        String viewType,
        String url,
        String[] tables, 
        String framework
    ) throws Exception{
        generateAllEntity(path, tables, packageName ,entity, framework);
        generateAllRepository(path, tables, packageName , entity, repository, framework);
        generateAllController(path, tables, packageName, entity, controller, repository, framework);  
        
        generateAllView(path, tables, view, viewType, url);    
    }
}
