import Image from "next/image";
import Link from "next/link";
import { postPathsQuery, postQuery } from "../../../../../sanity/lib/queries";
import { client } from "../../../../../sanity/lib/client";
import { urlForImage } from "../../../../../sanity/lib/image";
import PortableText from "react-portable-text";

// import RichTextComponents from "@/components/RichTextComponents";

type Props = {
  params: {
    slug: string;
  };
};

export const revalidate = 30 
export async function generateStaticParams() {
  const slugs: Post[] = await client.fetch(postPathsQuery);
  const slugRoutes = slugs.map((slug) => slug.slug.current);
  return slugRoutes.map((slug) => ({
    slug: slug,
  }));
}

async function Post({ params: { slug } }: Props) {
  const post = await client.fetch(postQuery, { slug });
  return (
    <article className="px-10 pb-28">
      <section className="space-y-2 border border-[#f7ab0a] text-white">
        <div className="relative min-h-56 flex flex-col md:flex-row justify-between">
          <div className="absolute top-0 h-full w-full opacity-10 blur-sm p-10">
            <Image
              className="object-cover object-center mx-auto"
              src={urlForImage(post.mainImage).url()}
              alt={post.author.name}
              fill
            />
          </div>
        </div>
        <section className="p-5 bg-[#f7ab0a] w-full">
          <div className="flex flex-col md:flex-row justify-between gap-y-5">
            <div>
              <h1 className="text-4xl font-extrabold">{post.title}</h1>
              <p>
                {" "}
                {new Date(post._createdAt).toLocaleDateString("en-US", {
                  day: "numeric",
                  month: "long",
                  year: "numeric",
                })}
              </p>
            </div>

            <div className="flex items-center space-x-2">
              <Image
                className="rounded-full"
                src={urlForImage(post.author.image).url()}
                alt={post.author.name}
                height={40}
                width={40}
              />
              <div className="w-64 ">
                <h3 className="text-lg font-bold">{post.author.name}</h3>
                <div>{/* Author Bio */}</div>
              </div>
            </div>
          </div>

          <div>
            <h2 className="italic pt-10">{post.description}</h2>
            <div className="flex items-center justify-end space-x-2">
              {/* {post.categories.map(({ category }: any) => (
                <p
                  key={category._id}
                  className="bg-gray-800 px-3 py-1 mt-4 rounded-full text-sm text-white font-semibold"
                >
                  {category.title}
                </p>
              ))} */}
            </div>
          </div>
        </section>
      </section>

      {/* <PortableText value={post.body} components={RichTextComponents}/> */}
      <PortableText
        content={post.body}
        projectId={process.env.SANITY_PROJECT_ID}
        dataset={process.env.SANITY_DATASET}
        serializers={{
          h1: ({ children }: any) => (
            <h1 className="text-5xl py-10 font-bold">{children}</h1>
          ),
          h2: ({ children }: any) => (
            <h2 className="text-4xl py-10 font-bold">{children}</h2>
          ),
          h3: ({ children }: any) => (
            <h3 className="text-3xl py-10 font-bold">{children}</h3>
          ),
          h4: ({ children }: any) => (
            <h4 className="text-4xl py-10 font-bold">{children}</h4>
          ),
          blockquote: ({ children }: any) => (
            <blockquote className="border-l-[#f7ab0a] border-l-4 pl-5 py-5 my-5">
              {children}
            </blockquote>
          ),
          image: ({ value }: any) => {
            return (
              <div className="relative w-full h-96 m-10 mx-auto ">
                <Image
                  src={urlForImage(value).url()}
                  alt="Blog Post Image"
                  fill
                />
              </div>
            );
          },
          ul: ({ children }: any) => (
            <ul className="ml-10 py-5 list-disc space-y-5">{children}</ul>
          ),
          ol: ({ children }: any) => (
            <ol className="ml-lg list-decimal">{children}</ol>
          ),
          link: ({ children, value }: any) => {
            const rel = !value.href.startsWith("/")
              ? "noreferrer noopener"
              : undefined;
            return (
              <Link
                href={value.href}
                rel={rel}
                className="underline decoration-[##f7ab0a] hover:decoration-black"
              >
                {children}
              </Link>
            );
          },

          // someCustomType: YourComponent,
        }}
      />
    </article>
  );
}

export default Post;
