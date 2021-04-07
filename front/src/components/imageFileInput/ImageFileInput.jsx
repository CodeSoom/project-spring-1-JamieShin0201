import React, { useRef, useState } from 'react';

import styles from './ImageFileInput.module.css';

const ImageFileInput = ({ imageUploader, url, index, onFileChange }) => {
  const [loading, setLoading] = useState(false);

  const inputRef = useRef();

  const onButtonClick = (event) => {
    event.preventDefault();
    inputRef.current.click();
  };

  const onChange = async (event) => {
    setLoading(true);
    const uploaded = await imageUploader.upload(event.target.files[0]);
    setLoading(false);
    onFileChange({
      index,
      url: uploaded.secure_url,
    });
  };

  return (
    <div className={styles.container}>
      <input
        ref={inputRef}
        className={styles.input}
        type="file"
        accept="image/*"
        name="file"
        onChange={onChange}
      />
      {!loading && (
        <button
          type="button"
          className={styles.button}
          style={{
            background: { url } && `url(${url}) center/cover no-repeat`,
          }}
          onClick={onButtonClick}
        >
          {!url && 'No Image'}
        </button>
      )}
      {loading && <div className={styles.loading} />}
    </div>
  );
};

export default ImageFileInput;
