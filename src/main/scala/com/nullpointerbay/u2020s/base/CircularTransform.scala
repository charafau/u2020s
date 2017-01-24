package com.nullpointerbay.u2020s.base

import com.squareup.picasso.Transformation
import android.graphics._

case class CircularTransform() extends Transformation {
  override def transform(source: Bitmap): Bitmap = {

    val size = Math.min(source.getWidth, source.getHeight)

    val x = (source.getWidth - size) / 2
    val y = (source.getHeight - size) / 2

    val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
    if (squaredBitmap != source) {
      source.recycle()
    }

    val bitmap = Bitmap.createBitmap(size, size, source.getConfig)

    val canvas = new Canvas(bitmap)
    val paint = new Paint()

    val shader = new BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    paint.setShader(shader)
    paint.setAntiAlias(true)

    val r = size / 2f
    canvas.drawCircle(r, r, r, paint)

    squaredBitmap.recycle()

    bitmap
  }

  override def key() = "circle"
}
