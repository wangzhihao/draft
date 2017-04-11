package zhihaow.example.cliparser;

import java.util.*;
import com.beust.jcommander.*;

@Parameters(separators = "=", commandDescription = "Record changes to the repository")
class CommandCommit {

    @Parameter(description = "The list of files to commit")
    private List<String> files;

    @Parameter(names = "--amend", description = "Amend")
    private Boolean amend = false;

    @Parameter(names = "--author")
    private String author;

    @Parameter(names = { "--help", "-h"} )
    public boolean help = false;
}

@Parameters(commandDescription = "Add file contents to the index")
class CommandAdd {

    @Parameter(description = "File patterns to add to the index")
    private List<String> patterns;

    @Parameter(names = "-i")
    public Boolean interactive = false;

    @Parameter(names = { "--help", "-h"} )
    public boolean help = false;
} 

public class SubCommand {
    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

    @Parameter(names = { "--help", "-h"} )
    private boolean help = false;

    private static JCommander getParsedCommand(JCommander command){
        String name = command.getParsedCommand();
        if(name == null)return command;
        return command.getCommands().get(name);
    }
    public static void main(String ... args){
	SubCommand main = new SubCommand(); 
	CommandAdd add = new CommandAdd();
	CommandCommit commit = new CommandCommit();
        JCommander jCommander = new JCommander(main);
        List<String> commands = Arrays.asList("add", "commit"); 
        jCommander.addCommand("add", add);
        jCommander.addCommand("commit", commit);
	jCommander.setProgramName("SubCommandExample");
	jCommander.parse(args);

        getParsedCommand(jCommander).usage();
        System.out.println(getParsedCommand(jCommander).getParsedCommand());
        /*
	if (main.help) {
	    jCommander.usage();
	    return;
	}
        if(jCommander.getParsedCommand().equals("add")){
            System.out.println("interactive: " + add.interactive);
            if(add.help){
                jCommander.usage("add");
            }
        }
        if(jCommander.getParsedCommand().equals("commit")){
            if(commit.help){
                jCommander.usage("commit");
            }
        }
        */
    }
}
