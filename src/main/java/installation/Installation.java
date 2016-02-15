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

		// 로직과 연결되어 사용될 템플릿 파일을 선택
		template = Velocity.getTemplate("src/main/resources/App/target/classes/batch.properties.vm");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out)); 

		// 값 입력
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
//        System.out.println("템플릿 생성 시작......");
//        t.merge(context, writer);
//        writer.flush();
//        writer.close();
//        System.out.println("템플릿 생성 종료......");
        
		
		// 템플릿과 컨텍스트를 렌더링해서 그 결과를 출력 
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
