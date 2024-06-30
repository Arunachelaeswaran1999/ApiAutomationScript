package api.endpoints;

public class Routes {
	
	//Base URL
	public static String base_url = "https://petstore.swagger.io/";
	
	//User Module endpoints
	public static String post_url = base_url+"v2/user";
	public static String get_url = base_url+"v2/user/{username}";
	public static String put_url = base_url+"v2/user/{username}";
	public static String delete_url = base_url+"v2/user/{username}";

}
