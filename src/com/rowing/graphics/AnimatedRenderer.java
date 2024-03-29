package com.rowing.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedRenderer extends Renderer{

	public Animation animation;
	public boolean loops;
	
	public AnimatedRenderer(boolean loops){	
		time = 0;
		this.loops = loops;
	}

	@Override
	public TextureRegion frame(float delta) {
		time += delta;
		return animation.getKeyFrame(time, loops);
	}
	
	public boolean getFinished(float max_time){
		if (max_time < time)
			return true;
		return false;
	}

}
