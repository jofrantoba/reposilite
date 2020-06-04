<template lang="pug">
  #app
    header
        Wave(:accentColor="this.configuration.accentColor").absolute.w-full
        a(href="/").flex.text-white.h-56.flex-col.justify-center.px-8.container.mx-auto
            .w-full
                h1.text-5xl.segoe.text-grey.font-bold.pt-1 {{ this.configuration.title }}
            .w-full
                p.text-lg.w-96.md_w-full {{ this.configuration.description }}
    main.mt-64.lg_mt-24
        .container.mx-auto
            .mx-4.pb-16
                .flex.justify-between.py-4
                    h1.text-xl 
                      | Index of 
                      span.ml-1 /{{ this.qualifier }}
                    router-link(
                        v-if="this.qualifier != undefined && this.qualifier.length > 0" 
                        :to='getParentPath()'
                    ) ← Back
                FileEntry(
                    v-if="hasFiles()" 
                    v-for="file in response.files" 
                    :key="file.name" 
                    :file="file"
                )
                h1(v-if="isEmpty()") Empty directory
                h1(v-if="!hasFiles()").font-bold {{ this.message }}

</template>

<script>
import Wave from '../components/Wave'
import FileEntry from '../components/FileEntry'

export default {
  data: () => ({
    configuration: {},
    qualifier: undefined,
    message: undefined,
    response: []
  }),
  components: {
    Wave,
    FileEntry
  },
  created() {
    this.message = window.REPOSILITE_MESSAGE

    this.$http
      .get(this.getApi('configuration'))
      .then(response => (this.configuration = response.data))
  },
  mounted() {
    this.updateEntities()
  },
  watch: {
    $route() {
       this.updateEntities()
    }
  },
  methods: {
    updateEntities() {
      this.qualifier = this.$route.params['qualifier']

      this.$http
        .get(this.getApi(this.qualifier))
        .then(response => (this.response = response.data))
    },
    hasFiles() {
      return this.response.files != undefined
    },
    isEmpty() {
      return this.hasFiles() && (this.response.files.length == 0)
    },
    getApi(path) {
      return ((process.env.NODE_ENV == 'production') ? '/' : 'http://localhost:80/') + 'api/' + path
    },
    getParentPath() {
        const elements = ('/' + this.qualifier).split('/')
        elements.pop()
        const path = elements.join('/')
        return path.length == 0 ? '/' : path
    }
  }
}
</script>

<style lang="stylus">  
html
  background-color: #f8f8f8
#app
  font-family 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale
  color #2c3e50
  height 100%
  width 100%
.blue
  background-color #0077dd
svg
  z-index -1
.segoe
  font-family 'Segoe UI', 'Manrope'
</style>