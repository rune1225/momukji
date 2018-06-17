package polymorphism;

public class SonySpeaker implements Speaker {
	
	
	public SonySpeaker() {
		System.out.println("===>SonySpeaker--객체 생성");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeUp()
	 */
	@Override
	public void volumeUp(){
		System.out.println("SonySpeaker--소리 올린다");
	}
	/* (non-Javadoc)
	 * @see polymorphism.Speaker#volumeDown()
	 */
	@Override
	public void volumeDown(){
		System.out.println("SonySpeaker--소리 내린다");
	}


}
