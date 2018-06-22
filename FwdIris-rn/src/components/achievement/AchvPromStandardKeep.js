import React from 'react';
import {StyleSheet, ScrollView,View,Text} from 'react-native';
import {AchvPromTarget} from './AchvPromTarget'
import {Sd2Am} from "./PromotionKeep/Sd2Am";
import {La2Am} from "./PromotionKeep/La2Am";
import {Sam2Ad} from "./PromotionKeep/Sam2Ad";
import {Ssm2Sd} from "./PromotionKeep/Ssm2Sd";
import {Sm2Am} from "./PromotionKeep/Sm2Am";
import {Am2Sam} from "./PromotionKeep/Am2Sam";
import {Sm2Smm} from "./PromotionKeep/Sm2Smm";
import {Ssm2Am} from "./PromotionKeep/Ssm2Am";
import {La2Sm} from "./PromotionKeep/La2Sm";

export class AchvPromStandardKeep extends React.Component {
    rankList = [
        {
            curRank:'LA',
            curRankName:'寿险顾问',
            nextRank:'AM',
            nextRankName:'业务经理',
            rankComponent : La2Am,
        },
        {
            curRank:'LA',
            curRankName:'寿险顾问',
            nextRank:'SM',
            nextRankName:'销售经理',
            rankComponent : La2Sm,
        },
        {
            curRank:'AM',
            curRankName:'业务经理',
            nextRank:'SAM',
            nextRankName:'资深业务经理',
            rankComponent : Am2Sam,
        },
        {
            curRank:'SAM',
            curRankName:'资深业务经理',
            nextRank:'AD',
            nextRankName:'总监',
            rankComponent : Sam2Ad,
        },
        {
            curRank:'SM',
            curRankName:'销售经理',
            nextRank:'SMM',
            nextRankName:'资深销售经理',
            rankComponent : Sm2Smm,
        },
        {
            curRank:'SM',
            curRankName:'销售经理',
            nextRank:'AM',
            nextRankName:'业务经理',
            rankComponent : Sm2Am,
        },
        {
            curRank:'SSM',
            curRankName:'资深销售经理',
            nextRank:'AM',
            nextRankName:'业务经理',
            rankComponent : Ssm2Am,
        },
        {
            curRank:'SSM',
            curRankName:'资深销售经理',
            nextRank:'SD',
            nextRankName:'销售总监',
            rankComponent : Ssm2Sd,
        },
        {
            curRank:'SD',
            curRankName:'销售总监',
            nextRank:'AM',
            nextRankName:'业务经理',
            rankComponent : Sd2Am,
        },
    ];
    getUserCurRank = ()=>{
        //for test
        const forTest = ['AM','LA','SAM','SM','SSM','SD'];
        return forTest[Math.random() * forTest.length | 0 ];
    }

    constructor() {
        super();

        const curRank ='LA'//this.getUserCurRank();
        const rankItemArr = this.getRankItem(curRank);
        const curItemIndex = 0;
        this.state = {
            curRank : curRank,
            rankItemArr : rankItemArr,
            curItemIndex : curItemIndex,
            data:[
                {
                    "userId": 111,"id": 111,"title": "init title1"
                },
                {
                    "userId": 122,"id": 222,"title": "init title2"
                }
            ]
        };
    }

    componentDidMount(){
        this.fetchData();
    }
    async fetchData() {
        console.log('***************Request Start********************');
        console.log('Waiting response.. .. ..');

        await fetch('http://jsonplaceholder.typicode.com/albums')
            .then(response => response.json())
            .then(json => {
                const data = [];
                for(var i=0;i<4;i++){
                    data.push(json[i]);
                }

                //4.构建&更新state
                this.setState({
                    data: data
                });
            })
            .catch((error) => {
                console.error(error);
            });

        console.log('***************Request End********************');
    }

    getRankItem = (cur, next) => {
        const rankItem = this.rankList.filter(item=>{
            if(next ==null )
                return item.curRank==cur;
            else
                return (item.curRank==cur)&&(item.nextRank==next);

        } );
        return rankItem;
    }
  render() {
      const {
          rankItemArr,
          curItemIndex ,
          data,
      } = this.state;

      const selectIndicator = rankItemArr.length>1;
      const rankItem = rankItemArr[curItemIndex];
      const Component = rankItem.rankComponent;
    return (
      <ScrollView style={{paddingVertical: 10,backgroundColor:"#F7F7F7"}}>
	      <View style={styles.container}>

              <View style={{paddingLeft:10,backgroundColor: 'white',height:40,justifyContent: 'center'}}>
                  <View>
                      <Text style={{fontSize:14}}>考核周期：2018/01/01~2018/03/31</Text>
                  </View>
                  <View>
                      <Text style={{fontSize:14}}>距离下次晋升时间：200天14时50分</Text>
                  </View>
              </View>

              <Component data={data}/>
	      </View>
      </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container :{
    flexDirection: 'column',
    marginLeft :5,
    marginRight :5,
    marginBottom :50,
  },
});

