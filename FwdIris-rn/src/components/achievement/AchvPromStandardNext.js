import React from 'react';
import {StyleSheet, ScrollView, View, Text,TouchableHighlight} from 'react-native';
import {La2Am} from "./PromotionNext/La2Am";
import {La2Sm} from "./PromotionNext/La2Sm";
import {Am2Sam} from "./PromotionNext/Am2Sam";
import {Sam2Ad} from "./PromotionNext/Sam2Ad";
import {Sm2Smm} from "./PromotionNext/Sm2Smm";
import {Sm2Am} from "./PromotionNext/Sm2Am";
import {Ssm2Am} from "./PromotionNext/Ssm2Am";
import {Ssm2Sd} from "./PromotionNext/Ssm2Sd";
import {Sd2Am} from "./PromotionNext/Sd2Am";

export class AchvPromStandardNext extends React.Component {

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
        const rankItems = this.rankList.filter(item=>{
            if(next ==null )
                return item.curRank==cur;
            else
                return (item.curRank==cur)&&(item.nextRank==next);

        } );
        return rankItems;
    }


    changeRankTarget = ()=>{
        const {
            rankItemArr : rankItemArr,
            curItemIndex : curItemIndex,
        } = this.state;

        const size = rankItemArr.length;
        if(size>1)
            this.setState({
                curItemIndex : (curItemIndex+1) % size
            });
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
            <ScrollView style={{paddingVertical: 10, backgroundColor: "#F7F7F7"}}>
                <View style={styles.container}>

                    <View style={{paddingLeft: 10, backgroundColor: 'white', height: 40, justifyContent: 'center'}}>
                        <View>
                            <Text style={{fontSize: 14}}>考核周期：
                            <Text style={{color: 'red'}}>2018/01/01~2018/03/31</Text>
                            </Text>
                        </View>
                        <View>
                            <Text style={{fontSize: 14}}>距离下次晋升时间：
                                <Text style={{color: 'red'}}>200</Text>
                                天
                                <Text style={{color: 'red'}}>14</Text>
                                时
                                <Text style={{color: 'red'}}>50</Text>
                                分
                            </Text>
                        </View>
                    </View>


                    <TouchableHighlight underlayColor='' onPress={this.changeRankTarget}>
                        <View style={{backgroundColor: 'white',marginTop:10}}>
                            {
                                selectIndicator && (
                                    (curItemIndex==1)?
                                   (
                                        <View style={{flexDirection: 'row',justifyContent: 'center',marginTop:10}}>
                                            <View style={{borderWidth:1,height:17,width:17,backgroundColor:'white',borderRadius:50,marginRight:10}}/>
                                            <View style={{borderWidth:1,height:17,width:17,backgroundColor:'#FF9300',borderRadius:50}}/>
                                        </View>
                                    )
                                    :
                                    (
                                        <View style={{flexDirection: 'row',justifyContent: 'center',marginTop:10}}>
                                            <View style={{borderWidth:1,height:17,width:17,backgroundColor:'#FF9300',borderRadius:50,marginRight:10}}/>
                                            <View style={{borderWidth:1,height:17,width:17,backgroundColor:'white',borderRadius:50}}/>
                                        </View>
                                    )
                                )
                            }

                        <View style={{
                            flexDirection: 'row',
                            justifyContent: 'center',
                            margin: 5,
                            backgroundColor: 'white',
                            height: 120
                        }}>
                            {/*<View style={{height:20,width:20,backgroundColor:'red',borderRadius:50}}/>*/}
                            <View style={{
                                flex: 4,
                                flexDirection: 'column',
                                justifyContent: 'center',
                                borderWidth: 1,
                                borderColor: '#999999',
                                marginTop: 5,
                                marginBottom: 5,
                                marginLeft: 15,
                                backgroundColor: '#F2F2F2'
                            }}>
                                <Text style={{fontWeight: 'bold', fontSize: 24, textAlign: 'center'}}>{rankItem.curRank}</Text>
                                <Text style={{
                                    fontWeight: 'bold',
                                    fontSize: 16,
                                    color: '#523333',
                                    textAlign: 'center'
                                }}>{rankItem.curRankName}</Text>
                            </View>
                            <View style={{flex: 1, flexDirection: 'column', justifyContent: 'center', margin: 5}}>
                                <Text style={{fontSize: 24, textAlign: 'center'}}>-></Text>
                            </View>
                            <View style={{
                                flex: 4,
                                flexDirection: 'column',
                                justifyContent: 'center',
                                borderWidth: 1,
                                borderColor: '#999999',
                                marginTop: 5,
                                marginBottom: 5,
                                marginRight: 15,
                                backgroundColor: '#F2F2F2',
                            }}>
                                <Text style={{fontWeight: 'bold', fontSize: 24, textAlign: 'center'}}>{rankItem.nextRank}</Text>
                                <Text style={{
                                    fontWeight: 'bold',
                                    fontSize: 16,
                                    color: '#523333',
                                    textAlign: 'center'
                                }}>{rankItem.nextRankName}</Text>
                            </View>
                        </View>
                        </View>
                    </TouchableHighlight>

                    <Component data={data}/>

                </View>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'column',
        marginLeft: 5,
        marginRight: 5,
        marginBottom: 50,
    },
});

