import React from 'react';
import {StyleSheet, ScrollView,View, Text} from 'react-native';
import { AnimatedCircularProgress } from 'react-native-circular-progress';

export class AchvPromTarget extends React.Component {

  render() {
  	const {
      percentage,
      itemTitl,
      itemValue,
      target,
      balance,
      isComplete
    } = this.props;

    return (
      <View style={{margin:5,flex:1,flexDirection: 'column',backgroundColor: 'white',width:160,height:170}}>
      	<View style={{flex:2,flexDirection: 'row',justifyContent: 'center'}}>
            <AnimatedCircularProgress
                size={65}
                width={2}
                fill={percentage}
                rotation={0}
                tintColor="#4FBDD5"
                backgroundColor="#DCDCDC">
                {
                    (fill) => (
                        <View >
                            <Text style={styles.points}>
                                {percentage}%
                            </Text>
                        </View>
                    )
                }
            </AnimatedCircularProgress>
        </View>
	      <View style={{flex:2,flexDirection: 'row',justifyContent: 'center',marginLeft:10,marginRight:10}}>
			  <View style={{flex:4,flexDirection: 'column',justifyContent: 'center',borderLeftWidth :0.5,borderBottomWidth :0.5,borderTopWidth:0.5, borderColor:'#999999',}}>
			    	<Text style={{fontSize:12,color:'#666666',textAlign:'center'}}>{itemTitl}</Text> 
			    	<Text style={{fontWeight :'bold',fontSize:16,color:'#FF9300',textAlign:'center'}}>{itemValue}</Text>
			  </View>
			  <View style={{flex:4,flexDirection: 'column',justifyContent: 'center',borderWidth :0.5,borderColor:'#999999',}}>
			    	<Text style={{marginBottom:5,fontSize:10,textAlign:'center'}}>标准：{target}</Text>
			    	<Text style={{fontSize:10,textAlign:'center'}}>差额：{balance}</Text>
			  </View>
		  </View>
		  <View style={{flex:1,justifyContent: 'center'}}>

		  {isComplete ?
		  	(<Text style={{fontSize:9,textAlign:'center'}}>该指标已达成，请继续保持！</Text> ) 
		  	:(<Text style={{fontSize:9,textAlign:'center'}}>该指标尚未达成，请继续努力！</Text> ) 
          }
		  	
		  </View>
	  </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});

