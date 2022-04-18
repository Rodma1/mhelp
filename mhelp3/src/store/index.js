import Vue from 'vue'
import Vuex from 'vuex'
import { getToken, setToken, removeToken } from "network/token.js"
import { loging, regist, getUserInfo, logout, updateUserInfo } from "network/loging.js"
import { SET_TOKEN, SET_NAME, SET_AVATAR, SET_ACCOUNT, SET_ID, SET_SCHOOL } from "./mutation-types.js"
import { getChatList, setChatList } from '../network/chatList.js'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    account: '',
    nickname: '',
    id: '',
    avatar: '',
    school: '',
    token: getToken(),
    chatList: getChatList(),
    msgContent: "",
    currentUser: {}
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
    },
    SET_SCHOOL(state, school) {
      return state.school = school
    },
    pushChatList(state, data) {
      setChatList(data);
      return state.chatList
    },
    saveCurrentUser(state, data) {
      return state.currentUser = data;
    },
    deleteCurrentUser(state) {
      return state.currentUser = {}
    }
  },
  actions: {
    //此处的{commit}绝不可省略
    login({ commit }, user) {
      return new Promise((resolve, reject) => {
        loging(user.account, user.password).then((res) => {
          if (res.success) {
            // console.log(res)
            commit(SET_TOKEN, res.data)
            setToken(res.data);
            resolve()
          }
          else {
            reject(res.msg)
            console.log(res.msg)

          }
        }).catch(err => {
          console.log(err)
        }
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
          alert(res.msg)
        }
      }).catch(err =>
        console.log(err))
    },
    getUserInfo({ commit, state }) {
      getUserInfo(state.token).then((res) => {
        if (res.success) {
          commit(SET_ACCOUNT, res.data.account);
          commit(SET_NAME, res.data.nickname);
          commit(SET_ID, res.data.id);
          commit(SET_AVATAR, res.data.avatar);
          commit(SET_SCHOOL, res.data.school)
          // resolve(res)
        }
        else {
          console.log(res)
        }
      }).catch(() => {
        commit('SET_TOKEN', '')
        commit('SET_ACCOUNT', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ID', '')
        commit('SET_SCHOOL', '')
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
          commit('SET_SCHOOL', '')
          removeToken()
        }

      })
    },
    fedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ACCOUNT', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ID', '')
        commit('SET_SCHOOL', '')
        removeToken()
        resolve()
      })
    },
    updateUserInfo({ commit }, data) {
      updateUserInfo(this.state.token, data).then((res) => {
        if (res.success) {
          commit(SET_NAME, data.nickname)
          commit(SET_SCHOOL, data.school)
          console.log(this.state.school)
          console.log(this.state.nickname)
          console.log(res)
        }
        else {
          console.log(res)
        }
      }).catch((err) => {
        console.log(err)
      })
    },
  },
  modules: {
  }
})
