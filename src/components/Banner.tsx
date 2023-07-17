function Banner() {
  return (
    <div className="flex flex-col lg:flex-row lg:space-x-5 justify-between font-bold px-10 py-5 mb-10">
      <div className="mt-5 md:mt-2">
        <h1 className="text-7xl">Micah&apos;s Daily Blog</h1>
        <h2>
          Welcome to{" "}
          <span className="underline decoration-4 decoration-[#F7AB0A]">
            Every developers
          </span>{" "}
          favorite blog
        </h2>
      </div>
      <p className="mt-5 md:mt-2 text-gray-400 max-w-sm">
        New Product Features | The Latest Technology | Weekly Debbuging
        Nightmares & Many More..
      </p>
    </div>
  );
}

export default Banner;
