/* eslint-disable no-return-await */
/* eslint-disable class-methods-use-this */

class ImageUploder {
  async upload(file) {
    const data = new FormData();
    data.append('file', file);
    data.append('upload_preset', process.env.REACT_APP_CLOUDINARY_UPLOAD_PRESET);
    const result = await fetch(
      process.env.REACT_APP_CLOUDINARY_UPLOAD_URL,
      {
        method: 'POST',
        body: data,
      },
    );

    return await result.json();
  }
}

export default ImageUploder;