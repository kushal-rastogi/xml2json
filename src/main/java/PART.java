/**
 * Created by Flash on 13-May-17.
 */
public class PART
{
    private String MODEL;

    private String ITEM;

    private String COST;

    private String MANUFACTURER;

    public String getMODEL ()
    {
        return MODEL;
    }

    public void setMODEL (String MODEL)
    {
        this.MODEL = MODEL;
    }

    public String getITEM ()
    {
        return ITEM;
    }

    public void setITEM (String ITEM)
    {
        this.ITEM = ITEM;
    }

    public String getCOST ()
    {
        return COST;
    }

    public void setCOST (String COST)
    {
        this.COST = COST;
    }

    public String getMANUFACTURER ()
    {
        return MANUFACTURER;
    }

    public void setMANUFACTURER (String MANUFACTURER)
    {
        this.MANUFACTURER = MANUFACTURER;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MODEL = "+MODEL+", ITEM = "+ITEM+", COST = "+COST+", MANUFACTURER = "+MANUFACTURER+"]";
    }
}