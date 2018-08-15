import React from 'react';

export class BaseScreenComponent extends React.Component {
  static navigationOptions = {
    header:null
  };

  constructor(props) {
    super(props);
  }
}