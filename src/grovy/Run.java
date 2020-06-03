package grovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import org.bluez.exceptions.*;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Run {
    public void Start() throws BluezNotReadyException, BluezAlreadyExistsException, BluezAuthenticationTimeoutException, BluezAlreadyConnectedException, BluezConnectionAttemptFailedException, BluezAuthenticationFailedException, BluezDoesNotExistException, BluezInProgressException, BluezInvalidArgumentsException, BluezAuthenticationCanceledException, BluezFailedException, IOException, BluezAuthenticationRejectedException, InterruptedException {

        System.out.print("------------------Start------------------------");

        Class<?> groovy = null;
        final File groovyFile = new File("C:\\Users\\SVMC\\IdeaProjects\\ManageO\\src\\main\\java\\grovy\\GroovyRun.txt");

        try {
//				groovy = classLoader.parseClass(groovyFile);
            groovy = CLASS_LOADER.parseClass(AccessController.doPrivileged(new PrivilegedAction<GroovyCodeSource>() {
                public GroovyCodeSource run() {
                    try {
                        CompilerConfiguration config = new CompilerConfiguration();
                        config.setScriptBaseClass(CLASS_LOADER.generateScriptName());
                        return new GroovyCodeSource(groovyFile, config.getSourceEncoding());
                    } catch (IOException e) {
                        throw new RuntimeException("Impossible to read the content of the input stream for file named: " + groovyFile.getName(), e);
                    }
                }
            }));
            if (groovy == null) {
                throw new IOException(String.format("Cannot compile %s.testcase  file."));
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }

        GroovyObject groovyObj = null;
        try {
            groovyObj = (GroovyObject) groovy.newInstance();
        } catch ( IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        DemoObject testClass = (DemoObject) groovyObj;

        //testClass.printHello();
        //System.out.println("Add: " + testClass.add(1, 2));
        testClass.turnOnBluetooth();
    }

    private static final GroovyClassLoader CLASS_LOADER = AccessController.doPrivileged(new PrivilegedAction<GroovyClassLoader>() {
        public GroovyClassLoader run() {
            return new GroovyClassLoader();
        }
    });
}
