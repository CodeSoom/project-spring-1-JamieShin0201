/* eslint-disable react/jsx-props-no-spreading */
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import ImageUploder from './services/image_uploader';
import ImageFileInput from './components/imageFileInput/ImageFileInput';

require('@fortawesome/fontawesome-free/js/all.js');

const imageUploader = new ImageUploder();
const FileInput = (props) => (
  <ImageFileInput {...props} imageUploader={imageUploader} />
);

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <App FileInput={FileInput} />
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root'),
);
