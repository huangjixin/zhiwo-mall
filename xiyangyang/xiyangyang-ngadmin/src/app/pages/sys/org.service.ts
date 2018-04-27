import {Injectable} from '@angular/core';
import {Org} from './org.model';

@Injectable()
export class OrgService {
  constructor() {
  }

  getData() {
    return [
      {
        'id': 1,
        'name': '广东',
        'children': [
          {
            'id': 3,
            'name': '广州',
          }, {
            'id': 4,
            'name': '佛山',
          }, {
            'id': 5,
            'name': '清远',
          }, {
            'id': 23,
            'name': '潮汕',
          }
        ]
      }, {
        'id': 6,
        'name': '海南',
        'children': [
          {
            'id': 7,
            'name': '海口',
          }, {
            'id': 8,
            'name': '三亚'
          }
        ]
      }, {
        'id': 15,
        'name': '香港',
        'children': [
          {
            'id': 16,
            'name': '铜锣湾',
          }, {
            'id': 17,
            'name': '屯门',
          }
        ]
      }
    ];
  }

  getComboData() {
    return [
      {
        'id': 1,
        'text': '广东',
        'children': [
          {
            'id': 3,
            'text': '广州',
          }, {
            'id': 4,
            'text': '佛山',
          }, {
            'id': 5,
            'text': '清远',
          }, {
            'id': 23,
            'text': '潮汕',
          }
        ]
      }, {
        'id': 6,
        'text': '海南',
        'children': [
          {
            'id': 7,
            'text': '海口',
          }, {
            'id': 8,
            'text': '三亚'
          }
        ]
      }, {
        'id': 15,
        'text': '香港',
        'children': [
          {
            'id': 16,
            'text': '铜锣湾',
          }, {
            'id': 17,
            'text': '屯门',
          }
        ]
      }
    ];
  }

  findById(id: String) {
    return new Org();
  }

  saveOrUpdate(org: Org) {

  }


}
