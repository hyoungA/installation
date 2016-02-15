package installation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class Installation {
	public static void main(String[] args) {
		Velocity.init("src/main/resources/velocity.properties");
		
		VelocityContext context = new VelocityContext();
		Template template = null; 

		// ������ ����Ǿ� ���� ���ø� ������ ����
		template = Velocity.getTemplate("src/main/resources/App/target/classes/batch.properties.vm");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 

		// �� �Է�
        context.put("LOG_ROOT", "${LOG_ROOT}");
        context.put("DB_TYPE", "${DB_TYPE}");		
        
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
        
		
		// ���ø��� ���ؽ�Ʈ�� �������ؼ� �� ����� ��� 
		if ( template != null) 
		  template.merge(context, writer);

		try {
			writer.flush(); 		
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
