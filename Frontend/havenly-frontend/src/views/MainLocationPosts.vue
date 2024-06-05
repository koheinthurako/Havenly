<template>
    <main class="">
        <div class="googleMapContainer col-12">
            <GoogleMapSearchVue @location-selected="handleLocationSelected"/>
        </div>
        <div class="locationPostsContainer col-12">
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
            },
        },

    }

</script>