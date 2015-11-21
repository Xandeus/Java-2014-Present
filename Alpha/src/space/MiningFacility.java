package space;

public class MiningFacility implements Building {
	CelestialBody body;
	public MiningFacility(CelestialBody body){
		this.body = body;
	}
	
	public String getType() {
		// TODO Auto-generated method stub
		return "Mining facility";
	}

	@Override
	public void function() {
		// TODO Auto-generated method stub

	}

	public CelestialBody getLocation() {
		// TODO Auto-generated method stub
		return body;
	}
	

}
