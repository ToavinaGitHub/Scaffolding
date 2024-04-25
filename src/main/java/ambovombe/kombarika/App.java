package ambovombe.kombarika;
import java.util.Scanner;

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.database.DbProperties;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.properties.DatabaseType;
import ambovombe.kombarika.utils.ConsoleColors;

import java.util.HashMap;;

public class App {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        String[] ports = {"5432", "3306"};

        String[] bddTypes = {"POSTGRESQL", "MYSQL"};

        String[] dataSources = {"postgresql", "mysql"};

        String[] users = {"postgres", "root"};
        String[] passwords = {"mdpProm15", ""};

        // Configuration de la base de données
        System.out.println(ConsoleColors.BLUE_BOLD + "-------------------------------Configuration de la base de données----------------------------------------------" + ConsoleColors.RESET);

       
        System.out.println("Choisissez la base de données (Par défaut: POSTGRESQL):");
        System.out.println("1) POSTGRESQL");
        System.out.println("2) MySQL");
        System.out.print(">> ");
        String dbChoice = scanner.nextLine();
        if (dbChoice.isEmpty()) {
            dbChoice = "1";
        }

        String databaseType = bddTypes[Integer.parseInt(dbChoice) - 1];

        System.out.println("Entrez l'adresse IP (Par défaut: localhost) :");
        System.out.print(">> ");
        String ip = scanner.nextLine();
        if (ip.isEmpty()) {
            ip = "localhost";
        }

        System.out.println("Entrez le port (Par défaut: " + ports[Integer.parseInt(dbChoice) - 1] + ") :");
        System.out.print(">> ");
        String port = scanner.nextLine();
        if (port.isEmpty()) {
            port = ports[Integer.parseInt(dbChoice) - 1];
        }

        System.out.println("Entrez le nom de la base de données (Par défaut district):");
        System.out.print(">> ");
        String database = scanner.nextLine();
        if (database.isEmpty()) {
            database = "district";
        }

        System.out.println("Entrez le nom d'utilisateur (Par défaut " + users[Integer.parseInt(dbChoice) - 1] + ") :");
        System.out.print(">> ");
        String username = scanner.nextLine();
        if (username.isEmpty()) {
            username = users[Integer.parseInt(dbChoice) - 1];
        }

        System.out.println("Entrez le mot de passe (Par défaut " + passwords[Integer.parseInt(dbChoice) - 1] + "):");
        System.out.print(">> ");
        String password = scanner.nextLine();
        if (password.isEmpty()) {
            password = passwords[Integer.parseInt(dbChoice) - 1];
        }

        String dataSource = "jdbc:" + dataSources[Integer.parseInt(dbChoice) - 1] + "://" + ip + ":" + port + "/" + database;

        System.out.println(ConsoleColors.YELLOW_BOLD + "Connexion à la base de données..." + ConsoleColors.RESET);
        DbProperties dbProperties = new DbProperties();
        dbProperties.setDatabase(database);
        dbProperties.setDatabaseType(DatabaseType.valueOf(databaseType));
        dbProperties.setDatasource(dataSource);
        dbProperties.setUsername(username);
        dbProperties.setPassword(password);

        DbConnection dbConnection = new DbConnection();
        try {
            dbConnection.setConnection(dbProperties.connect());
            System.out.println(ConsoleColors.GREEN+"Connexion à la base de données réussie"+ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED+"Erreur de connexion à la base de données : " + e.getMessage()+ConsoleColors.RESET);
            System.exit(1);
        }

        // Récupération des tables
        String[] tables = DbService.getAllTablesArrays(dbConnection);

        String[] frameworks = {"java:spring-boot", "java:spring"};
        String[] viewTypes = {"react-ionic", "thymeleaf"};

        // Configuration de Kombarika
        System.out.println(ConsoleColors.BLUE_BOLD+"---------------------------------Configuration de Kombarika-----------------------------------------"+ConsoleColors.RESET);

        System.out.println("Entrez le chemin de génération (Par défaut: './') :");
        System.out.print(">> ");
        String path = scanner.nextLine();
        if (path.isEmpty()) {
            path = "./";
        }

        System.out.println("Entrez le framework (par défaut 'java:spring-boot') :");
        
        for (int i = 0; i < frameworks.length; i++) {
            System.out.println((i + 1) + " ) " + frameworks[i]);
        }
        System.out.print(">>");
        String framework = scanner.nextLine();
        if (framework.isEmpty()) {
            framework = "1";
        }
        framework = frameworks[Integer.parseInt(framework) - 1];
       

        System.out.println("Entrez le nom du package (Par défaut: 'com.kombarika.mg') :");
        System.out.print(">> ");
        String packageName = scanner.nextLine();
        if (packageName.isEmpty()) {
            packageName = "com.kombarika.mg";
        }

        System.out.println("Entrez le chemin de génération des vues (Par défaut: 'views/view') :");
        System.out.print(">> ");
        String view = scanner.nextLine();
        if (view.isEmpty()) {
            view = "views/view";
        }

        System.out.println("Entrez le type de vue ('react-ionic') :");
        for (int i = 0; i < viewTypes.length; i++) {
            System.out.println((i + 1) + " ) " + viewTypes[i]);
        }
        System.out.print(">>");
        String viewType = scanner.nextLine();
        int indexV=0;
        if (viewType.isEmpty()) {
            viewType = "1";
        }else{
            indexV = Integer.parseInt(viewType)-1;
        }

        viewType = viewTypes[Integer.parseInt(viewType) - 1];
       
        System.out.println("Entrez l'URL (Par défaut: 'http://localhost:8080/') :");
        System.out.print(">> ");
        String url = scanner.nextLine();
        if (url.isEmpty()) {
            url = "http://localhost:8080/";
        }
        // Génération de classes
        System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------Génération des classes-----------------------------------------" + ConsoleColors.RESET);

        HashMap<String, DbProperties> listConnections = new HashMap<>();
        listConnections.put("DefaultConnection", dbProperties);
        dbConnection.setListConnection(listConnections);
        dbConnection.setInUseConnection("DefaultConnection");
        dbConnection.setDefaultConnection("DefaultConnection");

        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.setDbConnection(dbConnection);

        String entity = "Model";
        String controller = "Controller";
        String repository = "Repository";

        System.out.println("Liste des tables disponibles (Par défaut: toutes) :");
        for (int i = 0; i < tables.length; i++) {
            System.out.println((i + 1) + ") " + tables[i]);
        }
        System.out.println("Entrez le numéro des tables à générer séparées par des virgules (Par défaut: toutes) :");
        System.out.print(">> ");
        String table = scanner.nextLine();
        String[] splits = table.split(",");
        if(splits.length==1 && table.equals("")){
          
            if(indexV==1){
                codeGenerator.generateAllControllerThymeleaf("./", tables, packageName,"java:spring");
                codeGenerator.generateAllThymeleafView(path, tables, view, "thymeleaf", packageName);
            }else{
                codeGenerator.generateAll(path, packageName, entity, controller, repository, view, viewType, url, tables, framework);
            }
            
        }else if(splits.length==1){
            try{
                int index = Integer.parseInt(table)-1;
                String [] tabs = new String[1];
                tabs[0] = tables[index];

                if(indexV==1){
                    codeGenerator.generateAllControllerThymeleaf("./", tabs, packageName,"java:spring");
                    codeGenerator.generateAllThymeleafView(path, tabs, view, "thymeleaf", packageName);    
                }else{
                    codeGenerator.generateAll(path, packageName, entity, controller, repository, view, viewType, url, tabs, framework);
                }
            }catch(Exception e){
                System.out.println("Veuillez entrer un nombre valide");
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }else{
            String [] tabs = new String[splits.length];
            for(int i=0;i<splits.length;i++){
                try{
                    int index = Integer.parseInt(splits[i])-1;
                    tabs[i] = tables[index];
                }catch(Exception e){
                    System.out.println("Veuillez entrer un nombre valide");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
            if(indexV==1){
                codeGenerator.generateAllControllerThymeleaf("./", tabs, packageName,"java:spring");
                codeGenerator.generateAllThymeleafView(path, tabs, view, "thymeleaf", packageName);    
            }else{
                codeGenerator.generateAll(path, packageName, entity, controller, repository, view, viewType, url, tabs, framework);
            }
        }

        System.out.println(ConsoleColors.GREEN+"Génération terminée"+ConsoleColors.RESET);

    }
}
