import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import {createSvgIconsPlugin} from 'vite-plugin-svg-icons'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import UnoCSS from 'unocss/vite'

export default defineConfig(({mode}) => {
    const env = loadEnv(mode, process.cwd())
    return {
        base: env.VITE_APP_CONTEXT_PATH,
        server: {
            port: env.VITE_APP_PORT,
            cors: true,
            strictPort: true,
            proxy: {
                [env.VITE_APP_BASE_API]: {
                    target: 'http://localhost:26800',
                    changeOrigin: true,
                    rewrite: path => path.replace(new RegExp('^' + env.VITE_APP_BASE_API), ''),
                    secure: false,
                    ws: true,
                }
            },
        },
        resolve: {
            alias: {
                '@': path.resolve(__dirname, './src')
            }
        },
        plugins: [vue(), AutoImport({
            imports: ['vue', 'pinia', 'vue-router', {'axios': [['default', 'axios']]}, '@vueuse/core'],
            dirs: ['src/store/**', 'src/router/**', 'src/api/**', 'src/utils/**',],
            vueTemplate: true,
            resolvers: [ElementPlusResolver()],
            dts: 'dts/auto-imports.d.ts',
        }), Components({
            resolvers: [ElementPlusResolver()],
            types: [{
                from: 'vue-router',
                names: ['RouterLink', 'RouterView'],
            }],
            dirs: ['src/components/**', 'src/layout/**', 'src/views/**',],
            extensions: ['vue', 'md'], // allow auto import and register components used in markdown
            include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
            dts: 'dts/components.d.ts',
        }), createSvgIconsPlugin({
            // 指定需要缓存的图标文件夹
            iconDirs: [path.resolve(path.resolve(process.cwd()), 'src/assets/icons/svg')], // 指定symbolId格式
            symbolId: 'icon-[dir]-[name]',
        }), UnoCSS()
        ]
    }
})
