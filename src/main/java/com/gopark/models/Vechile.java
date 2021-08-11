package com.gopark.models;

public class Vechile
{

    private String type;
    private String terminal;
    private String vechileNo;

    public Vechile( String type, String terminal, String vechileNo )
    {
        super();
        this.type = type;
        this.terminal = terminal;
        this.vechileNo = vechileNo;
    }


    public String getType()
    {
        return type;
    }


    public void setType( String type )
    {
        this.type = type;
    }


    public String getTerminal()
    {
        return terminal;
    }


    public void setTerminal( String terminal )
    {
        this.terminal = terminal;
    }


    public String getVechileNo()
    {
        return vechileNo;
    }


    public void setVechileNo( String vechileNo )
    {
        this.vechileNo = vechileNo;
    }


    
}
