<template>
    <v-container class="mt-5 pt-5">
        <div class=" about-edit">
            <h1 class="mb-3">Discover Your Dream Property with <span>Havenly</span></h1>
            <p>
                Welcome to <span>Havenly</span>, your premier destination for all things real estate. Whether you're
                searching for the perfect home, a lucrative investment opportunity, or a strategic commercial space, our
                platform offers a comprehensive array of listings to suit every need and aspiration. With our
                user-friendly interface and powerful search tools, finding your dream property has never been more
                convenient.
            </p>
            <p>
                At <span>Havenly</span>, we pride ourselves on delivering exceptional service that goes beyond just
                property listings. Our team of dedicated professionals is committed to providing personalized guidance
                and expert advice throughout your real estate journey. From navigating the complexities of the market to
                negotiating the best deals, we're here to ensure that your experience is not only successful but also
                enjoyable.
            </p>
            <p>
                With <span>Havenly</span>, your real estate goals are within reach. Whether you're a first-time buyer or
                a seasoned investor, let us be your trusted partner in achieving your property aspirations. Explore our
                extensive database of listings, connect with our knowledgeable agents, and embark on your real estate
                journey with confidence. Welcome home to <span>Havenly</span>.
            </p>
        </div>
    </v-container>
</template>

<script>

export default {
    name: "AboutVue",

    data: () => ({
        getData: '',
    }),

    mounted() {
        localStorage.removeItem('openTab');
    },

    methods: {
        splitData(data) {
            this.getData = data.split(' ')[1];

            return this.getData;
        },
    },

}

</script>

<style>
.about-edit {
    p {
        text-indent: 60px;
    }

    span {
        color: #e86f52;
        font-weight: bold;
    }
}
</style>