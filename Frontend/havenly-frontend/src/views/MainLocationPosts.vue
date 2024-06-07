<template>
    <main class="">
        <div class="googleMapContainer col-12">
            <GoogleMapSearchVue @location-selected="handleLocationSelected"/>
        </div>
        <div class="locationPostsContainer col-12" ref="postsByLocation">
            <PostsByLocationVue :encryptedLocationId="encryptedLocationId"/>
        </div>
    </main>
</template>

<script>
    import GoogleMapSearchVue from './GoogleMapSearch.vue'
    import PostsByLocationVue from './PostsByLocation.vue'
    

    export default {
        name: 'MainLocationPosts',

        components: {
            GoogleMapSearchVue,
            PostsByLocationVue,
        },

        data() {
            return {
                encryptedLocationId: null,
            };
        },

        methods: {
            handleLocationSelected(encryptedId) {
                this.encryptedLocationId = encryptedId;
                setTimeout(() => {
                    const container = this.$refs.postsByLocation;
                    const scrollToElement = container.querySelector('.element-to-scroll-to');
                    if (scrollToElement) {
                        scrollToElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
                    }
                }, 200);
            },
        },

    }

</script>