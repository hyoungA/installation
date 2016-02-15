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

		// 로직과 연결되어 사용될 템플릿 파일을 선택
		app_batch_properties_template = Velocity.getTemplate("resources/App/target/classes/batch.properties.vm");
		app_jdbc_properties_template = Velocity.getTemplate("resources/App/target/classes/jdbc.properties.vm");


		templateList.add(app_batch_properties_template);
		templateList.add(app_jdbc_properties_template);
		
		// properties 값 입력
        context.put("BATCH_LOG_DIRECTORY", Velocity.getProperty("APP_BATCH_LOG_DIRECTORY"));
        context.put("BATCH_DATABASE_TYPE", Velocity.getProperty("APP_BATCH_DATABASE_TYPE"));
        context.put("FRW_JDBC_DRIVERCLASSNAME", Velocity.getProperty("APP_FRW_JDBC_DRIVERCLASSNAME"));
        
        // 템플릿별 파일 생성
        for(Template t : templateList){

        	try {
        		// 일단 경로는 현재 폴더의 .vm 을 제거한 파일로 생성.
            	f = new FileWriter(t.getName().substring(0, t.getName().length() - 3));
			} catch (Exception e ) {
    			e.printStackTrace();
			}
        	
        	BufferedWriter writer = new BufferedWriter(f);
//    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 
        	
        	// 템플릿과 컨텍스트를 렌더링해서 그 결과를 출력 
    		if ( t != null)
    		  t.merge(context, writer);

    		// 결과 쓰기
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
//        System.out.println("템플릿 생성 시작......");
//        t.merge(context, writer);
//        writer.flush();
//        writer.close();
//        System.out.println("템플릿 생성 종료......");
        
		
		
	}
}
