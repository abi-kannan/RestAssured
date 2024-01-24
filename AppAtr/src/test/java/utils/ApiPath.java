package utils;

public enum ApiPath {
	
	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json");
	private String methodName;

	ApiPath(String methodName) {
		this.methodName = methodName;
	}
	
	public String getApiPath() {
		return methodName;
	}
}
