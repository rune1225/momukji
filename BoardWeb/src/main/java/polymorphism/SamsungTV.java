package polymorphism;

public class SamsungTV implements TV {
	
	private Speaker speaker;
	private int price;

	public SamsungTV() {
		System.out.println("===>SamsungTV(1)--객체 생성");
	}
	
	public SamsungTV(Speaker speaker) {
		System.out.println("===>SamsungTV(2)--객체 생성");
		this.speaker = speaker;
	}
	

	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===>setSpeaker--객체 생성");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===>setSpeaker--객체 생성");
		this.price = price;
	}


	public SamsungTV(Speaker speaker, int price) {
		System.out.println("===>SamsungTV(3)--객체 생성");
		this.speaker = speaker;
		this.price = price;
	}

	public void powerOn() {
		System.out.println("SamsungTV--전원켠다 (가격:"+price+")");
	}

	public void powerOff() {
		System.out.println("SamsungTV--전원끈다");
	}

	public void volumeUp() {
		speaker.volumeUp();
		//System.out.println("SamsungTV--볼륨올린다");
	}

	public void volumeDown() {
		speaker.volumeDown();
		//System.out.println("SamsungTV--볼륨내린다");
	}

}
