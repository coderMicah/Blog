import Image from "next/image";
import { urlForImage } from "../../sanity/lib/image";
import ClientSideRoute from "./ClientSideRoute";

type Props = {
  posts: Post[];
};

function BlogList({ posts }: Props) {
  return (
    <div className="px-5 md:px-10">
      <hr className="border-[#f7ab0a] mb-10 " />

      <div className="grid grid-cols-1 md:grid-cols-2 gap-10 gap-y-16 pb-24">
        {posts.map((post) => (
          <ClientSideRoute key={post._id} route={`post/${post.slug.current}`}>
            <div className="flex flex-col group cursor-pointer ">
              <div
                className="relative w-full h-80 drop-shadow-xl 
            group-hover:scale-105 transition-transfor duration-200 ease-out"
              >
                <Image
                  className="object-cover object-left lg:object-center"
                  src={urlForImage(post.mainImage).url()}
                  alt={post.author.name}
                  fill
                />

                <div
                  className="absolute bottom-0 w-full bg-opacity-20
                 bg-black backdrop-blur-lg rounded drop-shadow-lg text-white 
                  p-5 flex justify-between"
                >
                  <div>
                    <p className="font-bold">{post.title}</p>
                    <p>
                      {new Date(post._createdAt).toLocaleDateString("en-US", {
                        day: "numeric",
                        month: "long",
                        year: "numeric",
                      })}
                    </p>
                  </div>
                  <div className="flex flex-col md:flex-row gap-y-2 md:gap-x-2 items-center">
                    {/* {post.categories.map(({ category }: any) => (
                      <div
                        key={category._id}
                        className="bg-[#F7AB0A] text-center text-black px-3 py-1 rounded-full text-sm font-semibold"
                      >
                        <p>{category.title}</p>
                      </div>
                    ))} */}
                  </div>
                </div>
              </div>
              <div className="mt-5 flex-1">
                <p className="underline text-lg font-bold">{post.title}</p>
                <p className="line-clamp-3 text-gray-500">{post.description}</p>
              </div>

              <p className="flex items-center font-bold mt-5 group-hover:underline">
                Read Post
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="ml-2 w-6 h-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M4.5 19.5l15-15m0 0H8.25m11.25 0v11.25"
                  />
                </svg>
              </p>
            </div>
          </ClientSideRoute>
        ))}
      </div>
    </div>
  );
}

export default BlogList;
