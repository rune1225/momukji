package polymorphism;

import org.springframework.stereotype.Component;

public class AppleSpeaker implements Speaker {
	
	public AppleSpeaker() {
		System.out.println("===>AppleSpeaker--객체 생성");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeUp()
	 */
	@Override
	public void volumeUp(){
		System.out.println("AppleSpeaker--소리 올린다");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeDown()
	 */
	@Override
	public void volumeDown(){
		System.out.println("AppleSpeaker--소리 내린다");
	}


}
