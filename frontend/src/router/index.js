import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'



import Index from '../components/IndexPage'
import Show from '../components/ShowPage'
import Write from '../components/WritePage'
import Reviews from '../components/ReviewsPage'
import ReviewUpload from '../components/ReviewWritePage'
import ReviewDetail from '../components/ReviewDetailPage'
import ReviewEdit from '../components/EditPage'
import SignUp from '../components/SignUpPage'
import Login from '../components/LoginPage'

Vue.use(Router)

export default new Router({
  mode : 'history',
  routes: [
    {
      path: '/movie',
      name: 'index',
      component: Index
    },
    {
      path: '/movie/:id',
      name: 'show',
      component: Show
    },
    {
      path: '/',
      name: 'index',
      component: Index
    },

    {
      path: '/movie/upload',
      name: 'write',
      component: Write
    },
    {
      path: '/movie/reviews',
      name: 'reviews',
      component: Reviews
    },
    {
      path: '/movie/reviews/:id',
      name: 'reviewDetail',
      component: ReviewDetail
    },
    {
      path: '/movie/reviews/upload',
      name: 'reviewUpload',
      component: ReviewUpload
    },
    {
      path: '/movie/reviews/edit',
      name: 'reviewEdit',
      component: ReviewEdit
    },
    {
      path: '/signUp',
      name: 'signUp',
      component: SignUp
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
