package json;

public class JSONArray 
{
	private StringBuilder sb;
	
	public JSONArray()
	{
		this.sb = new StringBuilder("[");
	}
	
	private void comma()
	{
		if (sb.length() > 1)
			sb.append(",");
	}

	public JSONArray add(int v)
	{
		comma();
		sb.append(v);
		return this;
	}

	public JSONArray add(long v)
	{
		comma();
		sb.append(v);
		return this;
	}

	public JSONArray add(boolean b)
	{
		comma();
		sb.append(b);
		return this;
	}

	public JSONArray add(JSONArray arr)
	{
		comma();
		sb.append(arr.toString());
		return this;
	}
	
	public JSONArray add(String s)
	{
		comma();
		sb.append("\"" + s + "\"");
		return this;
	}
	
	public JSONArray add(JSONObject obj)
	{
		comma();
		sb.append(obj.toString());
		return this;
	}
	
	public String toString()
	{
		return sb.toString() + "]";
	}
}