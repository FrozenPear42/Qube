package com.bugfullabs.qube.util;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.VerticalAlign;


public class AlignedText extends ChangeableText {
	
    private HorizontalAlign 	mAlignmentH;
    private VerticalAlign		mAlignmentV;
    
    private float offsetX = 0.0f;
    private float offsetY = 0.0f;
    
    private int screenWidth;
    private int screenHeight;
    
    public AlignedText(float pOffsetX, float pOffsetY, Font pFont, String pText, HorizontalAlign pHorizontalAlign, VerticalAlign pVerticalAlign, float width, float height) {
            super(pOffsetX, pOffsetY, pFont, pText, pHorizontalAlign, 255);
            
            this.mAlignmentH = pHorizontalAlign;
            this.mAlignmentV = pVerticalAlign;
            
            this.offsetX = pOffsetX;
            this.offsetY = pOffsetY;
            
            this.screenWidth = (int) width;
            this.screenHeight = (int) height;
            
            this. alignText();
    }
    
    private void alignText(){
            float textwidth 	= getWidth();
            float textheight	= getHeight();
            
            float x = offsetX;
            float y = offsetY;
                
            if(mAlignmentH == HorizontalAlign.CENTER)  {
            	x += ((this.screenWidth / 2) - (textwidth / 2)); 	
            }
            
            if(mAlignmentH == HorizontalAlign.RIGHT)  {
            	x += (this.screenWidth - textwidth); 	
            }
            
            if(mAlignmentV == VerticalAlign.CENTER) {
            	y += ((this.screenHeight / 2) - (textheight / 2)); 	
            }
            
            if(mAlignmentV == VerticalAlign.BOTTOM) {
            	y += (this.screenHeight - textheight);
            }

            
            setPosition(x, y);
    }

    @Override
    public void setText(String pText) {
            super.setText(pText);
            alignText();
    }
}
