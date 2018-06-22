import { AppRegistry } from 'react-native';
import App from './App';
//import App from './test/App';

//Close Warning in debug model
console.disableYellowBox = true;
console.disableRedBox = true;
AppRegistry.registerComponent('FwdIRIS', () => App);
