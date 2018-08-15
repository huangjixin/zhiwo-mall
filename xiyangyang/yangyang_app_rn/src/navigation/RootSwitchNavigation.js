import NavigationConstants from "./NavigationConstants";
import { createSwitchNavigator, createStackNavigator } from 'react-navigation';
import HomeScreen from '../modules/home/components/HomeScreen';
import SignInScreen from '../modules/home/components/SignInScreen';
import AuthLoadingScreen from '../modules/home/components/AuthLoadingScreen';

// 取出需要根导航的架构。
// const Auth = NavigationConstants.Auth;
// const AuthLoading = NavigationConstants.AuthLoading;
// const App = NavigationConstants.App;
// const SignIn = NavigationConstants.SignIn;
// const Home = NavigationConstants.Home;

const AuthStack = createStackNavigator({ SignIn: SignInScreen});
const AppStack = createStackNavigator({ Home: HomeScreen});

export const SwitchNavigation = createSwitchNavigator({
      Auth: AuthStack,
      AuthLoading: AuthLoadingScreen,
      App: AppStack,
    },
    {
      initialRouteName: 'AuthLoading',
    }
  );

// export default SwitchNavigation;