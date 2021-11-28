<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :lg="9" :xl="11">
      <div>
        <h2>多帮管理云端</h2>
        <el-image style="width: 100px;height: 100px" :src="require('@/assets/logo.png')"></el-image>
        <p>公众号 神的孩子都在歌唱</p>
        <p>扫码关注</p>
      </div>
    </el-col>
    <el-col :span="1">
      <el-divider direction="vertical"></el-divider>
    </el-col>
    <el-col :lg="9" :xl="11">
      <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="100px" class="demo-loginForm">
        <el-form-item label="用户名" prop="username">
          <el-input type="username" v-model="loginForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input class="el-input" v-model.number="loginForm.code" style="width: 150px;float: left"></el-input>
          <el-image class="captchatImg" :src="captchaImg" @click="getCaptcha"></el-image>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
          <el-button type="primary" @click="submitForm('loginForm')">注册</el-button>

        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import qs from 'qs'
export default {
  name: "login",
  data() {
    var checkCode = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('验证码不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          if (value < 18) {
            callback(new Error('必须是四位数字哦'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.loginForm.password !== '') {
          this.$refs.loginForm.validateField('password');
        }
        callback();
      }
    };
    return {
      loginForm: {
        username: '',
        password: '',
        //验证码
        code: '',
        token: ' '
      },
      rules: {
        username: [
          {validator: validatePass, trigger: 'blur'}
        ],
        code: [
          {validator: checkCode, trigger: 'blur'}
        ]
      },
      //验证码图片
      captchaImg: ''
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        // 将一个对象转变成字符串：username=111&password=111&code=111&token=aaaaa
        console.log(qs.stringify(this.loginForm))
        // console.log(this.loginForm)
        if (valid) {
          this.$axios.post('http://101.35.145.209:8889/login?'+qs.stringify(this.loginForm))
            .then(res=>{
              alert(res.data.msg);
            //  获取请求头的jwt
              const jwt = res.headers['authorization']
            //  将jwt存储到应用store中
              this.$store.commit("SET_TOKEN",jwt)
            //  跳转路由
              this.$router.push("/")
            })

        } else {
          //重新获取验证码
          this.getCaptcha()
          console.log('error submit!!');
          return false;
        }
      });

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //获取验证码
    getCaptcha() {
      this.$axios.get('http://101.35.145.209:8889/captcha').then(res => {
        // console.log(res.data);
        this.loginForm.token = res.data.data.token;
        this.captchaImg = res.data.data.captchaImg

      })
    },
  },
  created() {
    this.getCaptcha()
  }
}
</script>
<!--https://www.runoob.com/cssref/css3-pr-justify-content.html-->
<style scoped>
.el-col {
  /*弹性布局*/
  display: flex;
  /* 居中排列 */
  justify-content: center;
  height: 100vh;
  /*垂直排列*/
  align-items: center;
  /*文本居中对齐*/
  text-align: center;
}

.el-row {
  height: 100%;
  background-color: #fafafa;
}

.el-divider {
  height: 200px;
}

/*验证码样式*/
.captchatImg {
  float: left;
  /*设置元素的左边距*/
  margin-left: 8px;
  /*添加圆角的边框*/
  width: 90px;
  border-radius: 4px;
}

</style>
