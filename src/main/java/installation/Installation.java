package installation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class Installation {
	public static void main(String[] args) {
		Velocity.init("src/main/resources/velocity.properties");
		
		VelocityContext context = new VelocityContext();
		List<Template> templateList = new ArrayList<Template>();
		
		Template app_batch_properties_template = null;
		Template app_jdbc_properties_template = null;
		
		// outfile writer 
    	FileWriter f = null;

		// ������ ����Ǿ� ���� ���ø� ������ ����
		app_batch_properties_template = Velocity.getTemplate("resources/App/target/classes/batch.properties.vm");
		app_jdbc_properties_template = Velocity.getTemplate("resources/App/target/classes/jdbc.properties.vm");


		templateList.add(app_batch_properties_template);
		templateList.add(app_jdbc_properties_template);
		
		// properties �� �Է�
        context.put("BATCH_LOG_DIRECTORY", Velocity.getProperty("APP_BATCH_LOG_DIRECTORY"));
        context.put("BATCH_DATABASE_TYPE", Velocity.getProperty("APP_BATCH_DATABASE_TYPE"));
        context.put("FRW_JDBC_DRIVERCLASSNAME", Velocity.getProperty("APP_FRW_JDBC_DRIVERCLASSNAME"));
        
        // ���ø��� ���� ����
        for(Template t : templateList){

        	try {
        		// �ϴ� ��δ� ���� ������ .vm �� ������ ���Ϸ� ����.
            	f = new FileWriter(t.getName().substring(0, t.getName().length() - 3));
			} catch (Exception e ) {
    			e.printStackTrace();
			}
        	
        	BufferedWriter writer = new BufferedWriter(f);
//    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 
        	
        	// ���ø��� ���ؽ�Ʈ�� �������ؼ� �� ����� ��� 
    		if ( t != null)
    		  t.merge(context, writer);

    		// ��� ����
    		try {
    			writer.flush(); 		
    			writer.close();

    		} catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
        }
//        String outFile= "src/main/java/sds/velocity/ClassTemplate.java";
//        File f = new File(outFile);
//        
//        if(!f.exists()){
//        	f.createNewFile();
//        }
        
//        
//        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
//        
//        BufferedWriter writer = new BufferedWriter(osw);
//        System.out.println("���ø� ���� ����......");
//        t.merge(context, writer);
//        writer.flush();
//        writer.close();
//        System.out.println("���ø� ���� ����......");
        
		
		
	}
}
