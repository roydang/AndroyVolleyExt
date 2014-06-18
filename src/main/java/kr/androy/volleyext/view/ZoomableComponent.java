package kr.androy.volleyext.view;
/**
 * An Interface which has a zoom state, so that it can be restorable.   
 */
public interface ZoomableComponent extends Zoomable, Restorable<ZoomInfo> {
	float getZoomLevel();
}
