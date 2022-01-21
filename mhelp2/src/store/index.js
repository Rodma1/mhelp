import Vue from 'vue'
import Vuex from 'vuex'
import { getToken, setToken ,removeToken} from "network/token.js"
import { loging, regist, getUserInfo, logout } from "network/loging.js"
import { SET_TOKEN, SET_NAME, SET_AVATAR, SET_ACCOUNT, SET_ID } from "./mutation-types.js"
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    account: '',
    nickname: '',
    id: '',
    avatar: '',
    token: getToken(),
    chatMsg:{
      
    }
  },
  mutations: {
    SET_TOKEN(state, data) {
      return state.token = data;
    },
    SET_ACCOUNT(state, account) {
      return state.account = account
    },
    SET_NAME(state, nickname) {
      return state.nickname = nickname
    },
    SET_ID(state, id) {
      return state.id = id
    },
    SET_AVATAR(state, avatar) {
      return state.avatar = avatar
    }
  },
  actions: {
    //此处的{commit}绝不可省略
    login({ commit }, user) {
      return new Promise((resolve, reject) => {
        loging(user.account, user.password).then((res) => {
          if (res.success) {
            commit(SET_TOKEN, res.data)
            setToken(res.data);
            resolve()
          }
          else {
            reject(res.msg)

          }
        }).catch(err =>{
          console.log(err)
          alert(err)}
          
        )
          
      })
    },
    regist({ commit }, user) {
      regist(user.account, user.nickname, user.password).then((res) => {
        if (res.success) {
          // console.log(res)
          commit(SET_TOKEN, res.data)
        }
        else {
          console.log(res.msg)
        }
      }).catch(err =>
        console.log(err))
    },
    getUserInfo({ commit, state }) {
      getUserInfo(state.token).then((res) => {
        if (res.success) {
          // console.log(res)
          commit(SET_ACCOUNT, res.data.account);
          commit(SET_NAME, res.data.nickname);
          commit(SET_ID, res.data.id);
          commit(SET_AVATAR, res.data.avatar);
          // resolve(res)
        }
        else {
          console.log(res)
        }
      }).catch(()=>{
        commit('SET_TOKEN', '')
        commit('SET_ACCOUNT', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ID', '')
      })
    },
    logout({ commit, state }) {
      logout(state.token).then((res) => {
        // console.log(res);
        if (res.success) {
          commit('SET_TOKEN', '')
          commit('SET_ACCOUNT', '')
          commit('SET_NAME', '')
          commit('SET_AVATAR', '')
          commit('SET_ID', '')
          removeToken()
        }

      })
    },
    fedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ACCOUNT', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ID', '')
        removeToken()
        resolve()
      })
    },
  },
  modules: {
  }
})
