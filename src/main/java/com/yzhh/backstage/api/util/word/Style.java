package com.yzhh.backstage.api.util.word;

public class Style {
	private boolean strike = false;
    private boolean bold = true;
    private boolean italic= false; 
    private String color= "00FF00"; 
    private boolean underLine = false;
    private String fontFamily ="微软雅黑";
    private int fontSize = 12;
    
	public boolean isStrike() {
		return strike;
	}
	public void setStrike(boolean strike) {
		this.strike = strike;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isUnderLine() {
		return underLine;
	}
	public void setUnderLine(boolean underLine) {
		this.underLine = underLine;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	} 
}
